package com.cxz.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/11/25 14:19
 */
public class EncryptUtil {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("1qaz2wsx".getBytes("UTF-8"));
        byte[] digest = md.digest();
        String s = "";
        s = byteArrayToHexString(digest);
        System.out.println(s);
        System.out.println(s.toUpperCase());
        System.out.println(s.length());
    }
    private static String byteArrayToHexString(byte[] bs){
        StringBuilder sb = new StringBuilder(32);
        for(byte x:bs) {
            if((x & 0xff)>>4 == 0) {
                sb.append("0").append(Integer.toHexString(x & 0xff));
            } else {
                sb.append(Integer.toHexString(x & 0xff));
            }
        }
        return sb.toString();
    }

}
