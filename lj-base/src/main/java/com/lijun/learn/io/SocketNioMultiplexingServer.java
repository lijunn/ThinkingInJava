package com.lijun.learn.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author : LiJun
 * @date : 2022-10-27 17:18
 **/
public class SocketNioMultiplexingServer {

    public static void main(String[] args) {
        try {

            // 获得一个文件描述符 fd7
            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(8090));
            ss.configureBlocking(false);

            /*
             * 创建多路复用器
             * select/poll: jvm里开辟一个数组
             * poll : epoll_create 在内核中创建红黑树
             */
            Selector selector = Selector.open();

            /*
             * 注册事件
             * select/poll：将 fd7 添加到 jvm 的数组里
             * epoll： 调用 epoll_ctl(fd3,ADD,fd7,EPOLLIN)
             */
            ss.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器启动了。。。。。");


            /*
             * 调用多路复用器
             * select() 或 select(0) : 没有事件时会一直阻塞
             * select(100) : 阻塞100毫秒
             *
             * select/poll : 调用内核的 select（fd4）,  poll(fd4)
             * epoll :  调用内核的 epoll_wait()
             */
            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                System.out.println("selector size :"+selectionKeys.size());
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    iterator.remove();

                    if (key.isAcceptable()){
                        //获得连接事件
                        acceptHandler(key);
                    }else if (key.isReadable()){
                        //可读事件
                        readHandler(key);
                    }else if (key.isWritable()){
                        //写事件
                        writableHandler(key);
                    }else if (key.isConnectable()){
                        System.out.println(key.toString()+"isConnectable");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void acceptHandler(SelectionKey key) {
        try {
            //这里的 ServerSocketChannel 就是一开始的创建的,只不过是存到 Selector 中现在取出来
            ServerSocketChannel ss = (ServerSocketChannel) key.channel();

            //获取客户端连接，并设置非阻塞
            SocketChannel socket = ss.accept();
            socket.configureBlocking(false);

            //将获取到的客户端连接注册到 selector ，并且设置为读事件
            socket.register(key.selector(),SelectionKey.OP_READ);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readHandler(SelectionKey key) {
        SocketChannel socket = null;
        try {
            //这里获取的 SocketChannel 就是 acceptHandler 方法中注册的 SocketChannel
            //因为注册了 SelectionKey.OP_READ ，所以当这个socket有数据可读时会被 selector.select() 到
            socket = (SocketChannel) key.channel();
            socket.configureBlocking(false);

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //读取数据
            while(true){
                int num = socket.read(buffer);
                if (num > 0){
                    //将数据写回客户端
                    socket.register(key.selector(),SelectionKey.OP_WRITE,buffer);

                    //打印数据
                    buffer.flip();
                    byte[] chars = new byte[buffer.limit()];
                    buffer.get(chars);
                    System.out.println("read from client: " + new String(chars));
                    buffer.clear();
                }else if (num == 0){
                    break;
                }else if (num == -1){
                    System.out.println("client close");
                    key.cancel();
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 写事件的触发机制：我们注册了写事件 并且 send-queue 有空间
     * 所以我们要注意写事件的注册时机，只有我们想写的时候才去注册。
     * 因为我们没有写的时候 send-queue 一直是空的，所以一旦注册了写事件，就会一直循环写事件，
     * 所以当我们写完时要及时调用个 key.cancel() 关闭事件，或者注册读事件
     */
    private static void writableHandler(SelectionKey key) {
        SocketChannel client = (SocketChannel) key.channel();
        try {
            client.configureBlocking(false);

            ByteBuffer buffer = (ByteBuffer) key.attachment();
            byte[] chars = new byte[buffer.limit()];
            buffer.get(chars);
            System.out.println("write to client: " + new String(chars));

            buffer.flip();
            while (buffer.hasRemaining()) {
                client.write(buffer);
            }
            buffer.clear();

            //重新注册读事件
            client.register(key.selector(),SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
