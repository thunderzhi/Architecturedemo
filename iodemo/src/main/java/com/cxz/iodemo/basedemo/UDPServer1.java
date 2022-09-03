package com.cxz.iodemo.basedemo;

import java.io.*;
import java.net.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 16:05
 */
public class UDPServer1 {
    public static void main(String[] args) throws IOException {
        new Thread(()->{
            try {
                DatagramSocket datagramSocket = new DatagramSocket(1234);
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                datagramSocket.receive(packet);
                System.out.println("server recv");
                String msg = new String(packet.getData(), "utf-8");
                System.out.println(msg);
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
