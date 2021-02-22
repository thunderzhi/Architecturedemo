package com.cxz;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/21 21:58
 */
@mytestAnnotation(getValue = "this annotation on clazz")
public class annotationdemo {

    @mytestAnnotation(getValue = "this annotation on field")
    public String field1;

    @mytestAnnotation(getValue = "annotation on method")
    public void method1() {}

    @mytestAnnotation( )
    public  void defaultmethod(){}

}
