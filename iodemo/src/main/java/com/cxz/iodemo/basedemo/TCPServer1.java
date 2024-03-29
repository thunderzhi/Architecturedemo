package com.cxz.iodemo.basedemo;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 16:05
 */
public class TCPServer1 {
    public static void main(String[] args) throws IOException {
        new Thread(()->{
            try {
                ServerSocket ss = new ServerSocket(1234);
                while (true) {
                    System.out.println("启动服务器....");
                    Socket s = ss.accept();
                    System.out.println("客户端:" + s.getInetAddress().getLocalHost() + "已连接到服务器");
                    BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    //读取客户端发送来的消息
                    String mess = br.readLine();
                    System.out.println("客户端：" + mess);
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
                    bw.write(mess + "\n");
                    bw.flush();
                }
            } catch (
            IOException e) {
                e.printStackTrace();
            }}).start();
    }
}
