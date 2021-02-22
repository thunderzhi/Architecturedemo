package com.cxz.reflectdemo;

import com.cxz.annotationdemo;
import com.cxz.mytestAnnotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/21 22:40
 */
public class annodemotest {
    public static void main(String[] args) throws  Exception {
         
        // 获取类上的注解
        Class<annotationdemo> clazz = annotationdemo.class;
        mytestAnnotation annotationOnClass = clazz.getAnnotation(mytestAnnotation.class);
        System.out.println(annotationOnClass.getValue());

        // 获取成员变量上的注解
        Field name = clazz.getField("field1");
        mytestAnnotation annotationOnField = name.getAnnotation(mytestAnnotation.class);
        System.out.println(annotationOnField.getValue());

        // 获取hello方法上的注解
        Method hello = clazz.getMethod("method1", (Class<?>[]) null);
        mytestAnnotation annotationOnMethod = hello.getAnnotation(mytestAnnotation.class);
        System.out.println(annotationOnMethod.getValue());

        // 获取defaultMethod方法上的注解
        Method defaultMethod = clazz.getMethod("defaultmethod", (Class<?>[]) null);
        mytestAnnotation annotationOnDefaultMethod = defaultMethod.getAnnotation(mytestAnnotation.class);
        System.out.println(annotationOnDefaultMethod.getValue());
    }
}
