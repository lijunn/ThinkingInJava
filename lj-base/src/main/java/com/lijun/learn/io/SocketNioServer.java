package com.lijun.learn.io;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

/**
 * @author : LiJun
 * @date : 2022-10-24 16:17
 **/
public class SocketNioServer {
    public static void main(String[] args) {
        //客户端连接 socket 集合
        ArrayList<SocketChannel> socketList = new ArrayList<>();
        ServerSocketChannel ss = null;
        try {
            ss = ServerSocketChannel.open();
            ss.bind(new InetSocketAddress(8090));
            //设置非阻塞
            ss.configureBlocking(false);

            while (true){
                SocketChannel socketChannel = ss.accept();
                if (socketChannel == null){
//                    System.out.println("no client ....");
                }else {
                    //设置读取数据非阻塞
                    socketChannel.configureBlocking(false);
                    //将已连接的 socket 保存
                    socketList.add(socketChannel);

                    Socket socket = socketChannel.socket();
                    System.out.println("client....ip:port: " + socket.getInetAddress().getHostAddress()+ ":"+socket.getPort());
                }

                //遍历已连接的 socket
                ByteBuffer data = ByteBuffer.allocate(1024);
                for (SocketChannel socket : socketList) {
                    //读取数据
                    int num = socket.read(data);
                    if (num > 0){
                        data.flip();
                        byte[] chars = new byte[data.limit()];
                        data.get(chars);

                        System.out.println("" + new String(chars));
                        data.clear();
                    }else if (num == -1){
                        System.out.println("client close");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert ss != null;
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
