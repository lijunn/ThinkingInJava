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
            ServerSocketChannel ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(8090));
            ss.configureBlocking(false);

            /*
             * 创建多路复用器
             * select/poll: 在jvm创建集合
             * poll : epoll_create 在内核中创建红黑树
             */
            Selector selector = Selector.open();

            //将 ss 注册为 accept 状态，并添加到 selector 中
            ss.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("服务器启动了。。。。。");

            //阻塞方法，selector 中存在有状态的 socket 则返回值大于0
            while (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                SelectionKey key = iterator.next();
                selectionKeys.remove(key);

                if (key.isAcceptable()){
                    //获得连接事件
                    acceptHandler(key);

                }else if (key.isReadable()){
                    //可读事件
                    readHandler(key);
                }else if (key.isConnectable()){
                    System.out.println(key.toString()+"isConnectable");
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
            int num = socket.read(buffer);
            //打印数据
            if (num > 0){
                buffer.flip();
                byte[] chars = new byte[buffer.limit()];
                buffer.get(chars);

                System.out.println("" + new String(chars));
                buffer.clear();
            }else if (num == -1){
                System.out.println("client close");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
