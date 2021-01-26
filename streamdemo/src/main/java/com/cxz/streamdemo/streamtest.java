package com.cxz.streamdemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/16 11:33
 */
public class streamtest {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());

        filtered.forEach(System.out::println);
        System.out.println("------------------------------------------------------------");

        List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
        List<Integer> collect = numbers.stream().filter(i -> i > 8).collect(Collectors.toList());
        // 获取对应的平方数
        List<Integer> squaresList = numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList());

        System.out.println("numbers "+numbers.toString());
        System.out.println("squaresList "+squaresList.toString());



        System.out.println("------------------------------------------------------------");
        Stream.of(1,2,3).map(v->v+1).flatMap(v->Stream.of(v*5,v*10)).forEach(System.out::println);
        System.out.println("----------");

        Arrays.stream(new String[] {"Hello", "World"})
                .forEach(System.out::println);
        // 输出"Hello\nWorld"到控制台
        System.out.println("----------");
        int sum = Arrays.stream(new int[] {1, 2, 3})
                .reduce((a, b) -> a + b)
                .getAsInt();
        System.out.println(sum);
        // "sum"的值是"6"


        ArrayList<Person> people = new ArrayList<>(Arrays.
                asList(new Person(1,"a"),
                        new Person(2,"b"),new Person(3,"c")));
        System.out.println(people.toString());
        Person person = people.stream().filter(f -> f.no == 2).collect(Collectors.toList()).get(0);
        person.setName("xxxxxxxxxx");
        System.out.println(people.toString());

        //.forEach(System.out::println);


        ArrayList<String> strings1 = null;// new ArrayList<>();
        for (String s : strings1) {
            System.out.println("ss");
        }
        
    }

}
