package com.cxz;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/21 21:51
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface mytestValAnnotation {
    // 8种基本数据类型
    int intValue();
    long longValue();
    // String
    String name();
    // 枚举
    ColorEnum colorname();
    // Class类型
    Class<?> clazz();
    // 注解类型
    mytestAnnotation2 annotation2();

    // 以上几种类型的数组类型
    int[] intValueArray();
    String[] names();
    String getValue() default  "annotation on def method";

    int num();
}

@interface mytestAnnotation2{

}

enum ColorEnum{
    RED,
    BLACK;
}