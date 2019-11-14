package com.thinking.in.java.nio;

import com.sun.prism.impl.BufferUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : LiJun
 * @date : 2019-11-01 15:22
 **/
public class NioClient {

    public static void main(String[] args){

        try {
            SocketChannel socketChannel = SocketChannel.open();

            InetSocketAddress address = new InetSocketAddress(InetAddress.getLocalHost(),8080);

            socketChannel.configureBlocking(false);
            socketChannel.connect(address);

            if (socketChannel.isConnected()){

                socketChannel.write(ByteBuffer.wrap("s".getBytes()));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
