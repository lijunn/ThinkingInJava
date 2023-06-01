package com.lijun.base.equals;//package com.thinking.in.java.equals;
//
//import javax.swing.tree.TreeNode;
//import java.awt.*;
//import java.io.IOException;
//import java.lang.reflect.Array;
//import java.net.Socket;
//import java.nio.channels.Selector;
//import java.util.HashMap;
//import java.util.Hashtable;
//
//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util.println;
//
///**
// * @author : LiJun
// * @date : 2019-07-15 15:08
// **/
//public class MainTest {
//
//    public static void main(String[] args){
//
//
//    }
//
//    interface ChannelHandler{
//        void channelReadable(Channel channel);
//        void channelWritable(Channel channel);
//    }
//    class Channel{
//        Socket socket;
//        Event event;//读，写或者连接
//    }
//
//    //IO线程主循环:
//    class IoThread extends Thread{
//        @Override
//        public void run(){
//            Channel channel;
//            while(channel= Selector.select()){//选择就绪的事件和对应的连接
//                if(channel.event==accept){
//                    try {
//                        Selector open = Selector.open();
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    registerNewChannelHandler(channel);//如果是新连接，则注册一个新的读写处理器
//                }
//                if(channel.event==write){
//                    getChannelHandler(channel).channelWritable(channel);//如果可以写，则执行写事件
//                }
//                if(channel.event==read){
//                    getChannelHandler(channel).channelReadable(channel);//如果可以读，则执行读事件
//                }
//            }
//        }
//        Map<Channel，ChannelHandler> handlerMap;//所有channel的对应事件处理器
//    }
//}
