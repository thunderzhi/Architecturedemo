package com.cxz;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/2/19 16:25
 */
public class demomain {
    public static void main(String[] args) {
        IAppContext annotationConfigServletWebServerApplicationContext
                = new AnnotationConfigServletWebServerApplicationContext();
        System.out.println("demomain start");
        refresh(annotationConfigServletWebServerApplicationContext);
        System.out.println("demomain end");

    }
    public static void refresh(ApplicationContext applicationContext){
        ((com.cxz.AbstractApplicationContext) applicationContext).refresh();
    }
}
