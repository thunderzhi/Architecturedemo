package com.cxz.proxydemo.proxy;

import java.lang.reflect.*;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2022/9/1 20:21
 */
public class DynamicAgent {

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("正在执行的方法："+method);
                return null;
            }
        };
        //生成一个动态代理类的Class对象
        Class proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(),Foo.class);
        Class proxyClass2 = Proxy.getProxyClass(Foo.class.getClassLoader(),Foo.class);
        //获取proxyClass类中带一个InvocationHandler参数的构造器
        Constructor constructor = proxyClass.getConstructor(InvocationHandler.class);
        Constructor constructor2 = proxyClass2.getConstructor(InvocationHandler.class);
        //调用构造器的newInstance方法来创建实例
        Foo f = (Foo)constructor.newInstance(handler);
        Foo f2 = (Foo)constructor2.newInstance(handler);
        System.out.println(f.getClass());
        System.out.println(f2.getClass());
        f.foo();
    }
}
