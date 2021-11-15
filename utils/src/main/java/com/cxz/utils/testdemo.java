package com.cxz.utils;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/11/15 19:08
 */
public class testdemo {

    public static void main(String[] args) {
        Stu  s = new Stu();
        s.setAge(11);
        s.setName("ttttt");
        RepsonseShell<Stu> rep = new RepsonseShell<>();
        rep.setContent(s);
        String json =JsonUtil.toJson(rep);
        System.out.println("JsonUtil = " + json );

        RepsonseShell<Stu> rep2 = JsonUtil.toObject(new RepsonseShell<Stu>().getClass(), json);

        System.out.println("rep2 = " + rep2);
        //rep = JsonUtil.toObject(new RepsonseShell<Stu>().getClass(), strres);
    }


}
