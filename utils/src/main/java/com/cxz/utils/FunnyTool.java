package com.cxz.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/25 11:07
 */
public class FunnyTool {
    public static class lcobj{
        public String translatedTitle;
        public String frontendId;
        public String titleSlug;
        public String title;
        public String difficulty;
        public long lastSubmittedAt;
        public int numSubmitted;
        public String lastSubmissionSrc;
        public String __typename;

        @Override
        public String toString() {
            return "lcobj{" +
                    "translatedTitle='" + translatedTitle + '\'' +
                    ", frontendId='" + frontendId + '\'' +
                    ", titleSlug='" + titleSlug + '\'' +
                    ", title='" + title + '\'' +
                    ", difficulty='" + difficulty + '\'' +
                    ", lastSubmittedAt=" + lastSubmittedAt +
                    ", numSubmitted=" + numSubmitted +
                    ", lastSubmissionSrc='" + lastSubmissionSrc + '\'' +
                    ", __typename='" + __typename + '\'' +
                    '}';
        }
    }

    public List<String> problemlist = new ArrayList<>();

    public static void main(String[] args) {
        String path = "D:\\xuezhi\\Documents\\20210825LeeCodeEasy";
        String txt = readfile(path);
        List<lcobj> list = JsonUtil.toList(lcobj.class, txt);

        int n = list.size();
        System.out.println("n = " + n);
        int randomnum = generateRandom(n);
        System.out.println("get random"+generateRandom(n));
        String str = list.get(randomnum).toString();
        System.out.println("str = " + str);


    }
 
    public static int generateRandom(int n){
        return new Random().nextInt(n);
    }

    public static String readfile(String fileName ){
        File file = new File(fileName);
        BufferedReader reader = null;
        StringBuilder sb = new StringBuilder();
        try {

            reader = new BufferedReader(new FileReader(file));


            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号

                line++;
                sb.append(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
        return sb.toString();
    }
}
