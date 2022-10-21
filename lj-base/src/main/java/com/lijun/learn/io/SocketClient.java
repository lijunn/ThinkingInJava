package com.lijun.learn.io;

import java.io.*;
import java.net.Socket;

/**
 * @author : LiJun
 * @date : 2022-10-20 15:17
 **/
public class SocketClient {

    public static void main(String[] args) {
        Socket client = null;
        try {
            client = new Socket("127.0.0.1", 8090);

            //设置发送缓冲
            client.setSendBufferSize(20);
            //是否不开启优化，如果开启优化则会缓冲到 SendBufferSize 大小才发送
            client.setTcpNoDelay(false);
            OutputStream out = client.getOutputStream();

            //读取屏幕输入
            InputStream in = System.in;
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while(true){
                String line = reader.readLine();
                if(line != null ){
                    byte[] bb = line.getBytes();
                    for (byte b : bb) {
                        out.write(b);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                assert client != null;
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
