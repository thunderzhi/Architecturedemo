package com.cxz.iodemo.net;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 16:03
 */
public class TCPClient1 {

    public static void main(String[] args) throws IOException {
        Socket s1 = new Socket("127.0.0.1",12345);    //构建IO
        Socket s2 = new Socket("127.0.0.1",22345);    //构建IO
        InputStream is1 = s1.getInputStream();
        OutputStream os1 = s1.getOutputStream();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os1));
        //向服务器端发送一条消息
        bw.write("s1 测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
        bw.flush();    //读取服务器返回的消息
        BufferedReader br = new BufferedReader(new InputStreamReader(is1));
        String mess = br.readLine();
        System.out.println("服务器s1："+mess);

        InputStream is2 = s2.getInputStream();
        OutputStream os2 = s2.getOutputStream();
        BufferedWriter bw2 = new BufferedWriter(new OutputStreamWriter(os2));
        //向服务器端发送一条消息
        bw2.write("s2 测试客户端和服务器通信，服务器接收到消息返回到客户端\n");
        bw2.flush();    //读取服务器返回的消息
        BufferedReader br2 = new BufferedReader(new InputStreamReader(is2));
        mess = br2.readLine();
        System.out.println("服务器s2："+mess);
    }
}
