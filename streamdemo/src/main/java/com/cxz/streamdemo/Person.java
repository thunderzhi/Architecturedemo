package com.cxz.streamdemo;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/16 16:44
 */
public class Person {

    public Person( int no,String name) {
        this.name = name;
        this.no = no;
    }

    public String getName() {
        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    private String name;
    public int no;

}
