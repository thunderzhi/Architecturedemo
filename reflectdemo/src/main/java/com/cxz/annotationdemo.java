package com.cxz;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/21 21:58
 */
@mytestAnnotation(getValue = "this annotation on clazz", num = 0)
public class annotationdemo {


    @mytestAnnotation(getValue = "this annotation on field", num = 0)
    public String field1;

    @mytestAnnotation(getValue = "annotation on method", num = 0)
    public void method1() {}

    @mytestAnnotation(num = 0)
    public  void defaultmethod(){}

}
