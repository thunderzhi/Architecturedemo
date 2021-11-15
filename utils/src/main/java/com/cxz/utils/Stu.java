package com.cxz.utils;

import java.io.Serializable;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/15 19:09
 */

public class Stu implements Serializable {
    //
    private int age;

    //
    private String  name;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
