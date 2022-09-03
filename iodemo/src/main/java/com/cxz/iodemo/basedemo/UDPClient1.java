package com.cxz.iodemo.basedemo;

import java.io.*;
import java.net.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/3 16:03
 */
public class UDPClient1 {

    public static void main(String[] args) {
        new Thread(()->{
            byte []arr = "Hello Server".getBytes();
            try {
                InetAddress inetAddress = InetAddress.getLocalHost();
                DatagramSocket datagramSocket = new DatagramSocket();
                DatagramPacket datagramPacket = new DatagramPacket(arr, arr.length, inetAddress, 1234);
                datagramSocket.send(datagramPacket);
                System.out.println("send end");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
