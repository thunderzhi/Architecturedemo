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
public @interface mytestAnnotation {

    String getValue() default  "annotation on def method";

    int num();
}
