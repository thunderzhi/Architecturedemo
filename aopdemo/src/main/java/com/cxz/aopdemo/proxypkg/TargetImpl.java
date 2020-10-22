package com.cxz.aopdemo.proxypkg;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/13 15:19
 */
public class TargetImpl implements Target {

    @Override
    public int test(int i) {
        return i + 1;
    }
}