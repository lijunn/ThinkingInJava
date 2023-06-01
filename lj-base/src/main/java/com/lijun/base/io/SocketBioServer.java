package com.lijun.base.io;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : LiJun
 * @date : 2022-10-20 14:59
 **/
public class SocketBioServer {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            //监听端口
            serverSocket = new ServerSocket(8090);

            //阻塞服务端方便测试，控制台回车后继续执行
            System.in.read();

            while (true){
                //阻塞获取 socket
                Socket socket = serverSocket.accept();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            char[] data = new char[1024];

                            while (true){
                                int num = reader.read(data);
                                if (num > 0){
                                    System.out.println("Server receive data: "+ num + " value: "+new String(data,0,num));
                                }else if (num == 0){
                                    System.out.println("Server receive nothing");
                                }else {
                                    System.out.println("Server receive -1");
                                    socket.close();
                                    break;
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert serverSocket != null;
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
