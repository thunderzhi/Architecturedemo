package com.cxz.iodemo.basedemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 15:15
 */
public class SocketHttpClient {
    public void start(String host, int port) {
        // 初始化 socket
        Socket socket = new Socket();
        try {
            // 设置 socket 连接
            SocketAddress remote = new InetSocketAddress(host, port);
            socket.setSoTimeout(5000);
            socket.connect(remote);
            // 发起请求
            PrintWriter writer = getWriter(socket);
            System.out.println(HttpUtil.compositeRequest(host));
            writer.write(HttpUtil.compositeRequest(host));
            writer.flush();
            // 读取响应
            String msg;
            BufferedReader reader = getReader(socket);
            String tname = Thread.currentThread().getName();
            while ((msg = reader.readLine()) != null){

                System.out.println("Thread:_ "+tname+" msg: "+ msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }
    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(new OutputStreamWriter(out));
    }
}

