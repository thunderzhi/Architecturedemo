package com.cxz;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/21 21:58
 */
@mytestValAnnotation(
        intValue = 0,
        longValue = 0L,
        name = "xxxxxxx",
        colorname = ColorEnum.RED,
        clazz = annotationdemo2.class,
        annotation2 = @mytestAnnotation2,
        intValueArray = {},
        names = {"a","b","c"},
        getValue = "this annotation on clazz", num = 0)
public class annotationdemo2 {




}
