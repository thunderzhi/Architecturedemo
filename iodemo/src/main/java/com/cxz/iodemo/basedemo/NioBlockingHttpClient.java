package com.cxz.iodemo.basedemo;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 16:55
 */
public class NioBlockingHttpClient {
    private SocketChannel socketChannel;
    private String host;

    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {

            NioBlockingHttpClient client = new NioBlockingHttpClient("www.baidu.com", 80);

            try {
                client.request();
            } catch (IOException e) {
                System.out.println("i:errr "+i);
                e.printStackTrace();
            }


            System.out.println("i: "+i);
            System.out.println("···································: ");
        }

    }

    public NioBlockingHttpClient(String host, int port) throws IOException {
        this.host = host;
        socketChannel = SocketChannel.open();
        socketChannel.socket().setSoTimeout(5000);
        SocketAddress remote = new InetSocketAddress(this.host, port);
        this.socketChannel.connect(remote);
    }

    public void request() throws IOException {
        PrintWriter pw = getWriter(socketChannel.socket());
        BufferedReader br = getReader(socketChannel.socket());

        pw.write(HttpUtil.compositeRequest(host));
        pw.flush();
        String msg;
        while ((msg = br.readLine()) != null){
            System.out.println(msg);
        }
    }

    private PrintWriter getWriter(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        return new PrintWriter(out);
    }

    private BufferedReader getReader(Socket socket) throws IOException {
        InputStream in = socket.getInputStream();
        return new BufferedReader(new InputStreamReader(in));
    }
}
