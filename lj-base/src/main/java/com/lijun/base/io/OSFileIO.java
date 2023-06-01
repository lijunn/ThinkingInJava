package com.lijun.base.io;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;


/**
 * @author lijun
 */
public class OSFileIO {

    static byte[] data = "123456789\n".getBytes();
    static String path =  "/home/testfileio/out.txt";

    public static void main(String[] args) throws Exception {
        switch ( args[0]) {
            case "0" :
                testBasicFileIO();
                break;
            case "1":
                testBufferedFileIO();
                break;
            case "2" :
                testRandomAccessFileWrite();
                break;
            case "3" :
                testBufferedNioOnHeap();
                break;
            case "4" :
                testBufferedNioOffHeap();
                break;
            case "5" :
                testBufferedNioMmp();
                break;
            default:
                whatByteBuffer();
        }
    }

    /**
     * 最基本的file写
     * 每次都会触发系统调用
     */
    public static  void testBasicFileIO() throws Exception {
        File file = new File(path);
        FileOutputStream out = new FileOutputStream(file);
        while(true){
            out.write(data);
        }
    }

    /**
     * 测试buffer文件IO
     *
     * 相当于jvm 维护了一个 8kb的字节数组
     * 每 8k（这个大小可以调整） 才调用一次 syscall（系统调用）
     * 减少了系统调用（即内核态和用户态的切换）
     */
    public static void testBufferedFileIO() throws Exception {
        File file = new File(path);
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file));
        while(true){
            out.write(data);
        }
    }

    /**
     * 测试RandomAccessFile
     * 可以根据 seek 跳转到不同的位置去读写
     */
    public static void testRandomAccessFileWrite() throws Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");

        raf.write("Test RandomAccessFileWrite\n".getBytes());
        raf.write("Test mashibing\n".getBytes());
        System.out.println("write------------");

        //暂停程序，方便查看文件
        System.in.read();

        //跳转到4这个位置开始写
        raf.seek(4);
        raf.write("ooxx".getBytes());
    }

    /**
     * 测试文件NIO(New IO)
     * on heap buffer : 缓存数组在jvm的堆中
     */
    public static void testBufferedNioOnHeap() throws  Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        //获取NIO 的 channel
        FileChannel channel = raf.getChannel();

        //初始缓存数组
        ByteBuffer buffer = ByteBuffer.allocate(8192);
        buffer.put("test on heap buffer".getBytes());

        //翻转buffer,读取buffer的内容然后写入PageCache
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
    }

    /**
     * 测试文件NIO(New IO)
     * off heap buffer : 缓存数组在java程序的进程中
     */
    public static void testBufferedNioOffHeap() throws  Exception {
        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        //获取NIO 的 channel
        FileChannel channel = raf.getChannel();

        //初始缓存数组
        ByteBuffer buffer = ByteBuffer.allocateDirect(8192);
        buffer.put("test off heap buffer".getBytes());

        //翻转buffer,读取buffer的内容然后写入PageCache
        buffer.flip();
        while (buffer.hasRemaining()) {
            channel.write(buffer);
        }
        channel.close();
    }

    /**
     * 测试文件NIO(New IO)
     * memory-mapped（内存映射） : 缓存数组的逻辑地址与 PageCache 中的页的地址映射
     * 直接将数据缓存到内核的 PageCache 中，不会产生syscall（系统调用，内核态用户态的切换）
     *
     * memory-mapped 的内存映射，依然是内核的PageCache体系所约束的！！
     * 可以用C写jni扩展库，使用linux内核的 Direct IO，直接IO是忽略linux的 PageCache，
     * 相当于把 PageCache  交给了程序自己开辟一个字节数组当作 PageCache，动用代码逻辑来维护一致性/dirty。。。一系列复杂问题
     */
    public static void testBufferedNioMmp() throws  Exception {

        RandomAccessFile raf = new RandomAccessFile(path, "rw");
        FileChannel rafChannel = raf.getChannel();

        //创建映射缓存
        MappedByteBuffer map = rafChannel.map(FileChannel.MapMode.READ_WRITE, 0, 4096);
        //写入数据
        map.put("Test memory-mapped".getBytes());

        System.out.println("map--put--------");
        System.in.read();
        //相当于 flush，强制刷脏，写入磁盘
        map.force();

        //打印内容
        map.flip();
        for (int i = 0; i < map.limit(); i++) {
            Thread.sleep(200);
            System.out.print(((char)map.get(i)));
        }
    }

    /**
     * ByteBuffer 的使用
     */
    public static void whatByteBuffer(){
        //on heap buffer
//        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //off heap buffer :
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);


        System.out.println("postition: " + buffer.position());
        System.out.println("limit: " +  buffer.limit());
        System.out.println("capacity: " + buffer.capacity());
        System.out.println("mark: " + buffer);

        buffer.put("123".getBytes());

        System.out.println("-------------put:123......");
        System.out.println("mark: " + buffer);

        //读写交替
        buffer.flip();

        System.out.println("-------------flip......");
        System.out.println("mark: " + buffer);

        buffer.get();

        System.out.println("-------------get......");
        System.out.println("mark: " + buffer);

        buffer.compact();

        System.out.println("-------------compact......");
        System.out.println("mark: " + buffer);

        buffer.clear();

        System.out.println("-------------clear......");
        System.out.println("mark: " + buffer);
    }

}
