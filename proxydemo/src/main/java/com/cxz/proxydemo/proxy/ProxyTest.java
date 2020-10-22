package com.cxz.proxydemo.proxy;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/15 16:58
 */
public class ProxyTest {
    public static void main(String[] args) throws Exception {
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        System.out.println(clazzProxy1);
//        printConstructors(clazzProxy1);
//        printMethods(clazzProxy1);
        createProxyInstance( );
    }
    public static void printConstructors(Class clazz){
        System.out.println("-------------constructors list-------------");
        Constructor[] constructors = clazz.getConstructors();
        System.out.print(getExecutableList(constructors));
    }
    public static void printMethods(Class clazz) {
        System.out.println("-------------methods list-------------");
        Method[] methods = clazz.getMethods();
        System.out.print(getExecutableList(methods));
    }
    public static String getExecutableList(Executable[] executables){
        StringBuilder stringBuilder = new StringBuilder();
        for (Executable executable : executables) {
            String name = executable.getName();
            stringBuilder.append(name);
            stringBuilder.append("(");
            Class[] clazzParams = executable.getParameterTypes();
            for (Class clazzParam : clazzParams) {
                stringBuilder.append(clazzParam.getName()).append(",");
            }
            if (clazzParams != null && clazzParams.length != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            stringBuilder.append(")\n");
        }
        return stringBuilder.toString();
    }

    private static void createProxyInstance( ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /**
         * 方法1：先创建代理类，再使用反射创建实例对象
         */
        Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
        Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
        Collection proxy1 = (Collection) constructor.newInstance(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });

        /**
         * 方法2：直接使用newProxyInstance方法创建实例对象
         */
        Collection proxy2 = (Collection)Proxy.newProxyInstance(
                Collection.class.getClassLoader(),
                new Class[]{Collection.class},
                new InvocationHandler() {
                    ArrayList target = new ArrayList();
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //ArrayList targetTmp = new ArrayList();
                        System.out.println("before invoke method: "+method.getName());
                        return method.invoke(target,args);

                    }
                });

        proxy2.add("aaa");
        proxy2.add("bbb");
        System.out.println(proxy2.size());
        System.out.println(proxy2);
        System.out.println("----------------");
        System.out.println(proxy2.getClass().getName());

    }
}
