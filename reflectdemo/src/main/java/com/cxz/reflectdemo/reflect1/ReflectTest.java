package com.cxz.reflectdemo.reflect1;

import java.util.Arrays;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/14 16:13
 */
public class ReflectTest {
    public static void main(String[] args) {
        int [] a1 = new int[]{1,2,3};
        int [] a2 = new int[5];
        int [][] a3 = new int[2][3];
        System.out.println(a1.getClass() == a2.getClass());//true
        System.out.println(a1.getClass());//class [I

        System.out.println("a1 hashcode:"+a1.getClass().hashCode());
        System.out.println("a2 hashcode:"+a2.getClass().hashCode());

        System.out.println("Equals: "+a1.getClass().equals(a2.getClass()));

        System.out.println(a3.getClass());//class [[I
        System.out.println(a1.getClass().getSuperclass() == a3.getClass().getSuperclass());//true
        System.out.println(a2.getClass().getSuperclass());//class java.lang.Object

        //Error:(24, 42) java: 不可比较的类型: java.lang.Class<capture#1, 共 ? extends int[]>和java.lang.Class<capture#2, 共 ? extends int[][]>
        //System.out.println(a1.getClass() == a3.getClass());

        Object []b3 = a3;//通过
        //下句编译不通过   Error:(17, 24) java: 不兼容的类型: int[]无法转换为java.lang.Object[]
        //Object [] b1 = a1;

        String s1 = "abc";
        System.out.println(Arrays.asList(a1));//[[I@7440e464]
        System.out.println(Arrays.asList(s1));//[abc]
    }
}
