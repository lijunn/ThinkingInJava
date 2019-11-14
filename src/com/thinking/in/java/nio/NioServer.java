package com.thinking.in.java.nio;

import com.sun.prism.impl.BufferUtil;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author : LiJun
 * @date : 2019-11-01 15:22
 **/
public class NioServer {

    public static ServerSocketChannel serverSock;

    public static void main(String[] args){

        System.out.println(stairs(10));

    }

    public static int stairs(int n){
        if (n == 1){
            return 1;
        }
        if (n == 2){
            return 2;
        }

        return stairs(n-2)+stairs(n-1);
    }




}
