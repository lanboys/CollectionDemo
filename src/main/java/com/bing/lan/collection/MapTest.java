package com.bing.lan.collection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by 蓝兵 on 2019/4/15.
 */
public class MapTest {

    public static void main(String[] args) {
        testHashMap();
        //testTreeMap();
    }

    /**
     * 测试hashcode相等, 但不equals时, 存放时底层是用单向链表数据结构
     */
    private static void testHashMap() {
        Map<Object, String> map = new HashMap<>();

        FooA fooA = new FooA(1);
        map.put(fooA, "fooValue");
        print(map);
        System.out.println("/////////////////////////////////////////////");
        FooB fooB = new FooB(1);
        map.put(fooB, "fooValue");
        print(map);
        System.out.println("/////////////////////////////////////////////");
        FooC fooC = new FooC(1);
        map.put(fooC, "fooValue");
        print(map);
        System.out.println("/////////////////////////////////////////////");
        fooA.setId(2);
        fooC.setId(3);
        print(map);

        boolean b = map.containsKey(fooC);
        System.out.println("testHashMap(): " + b);// false

        b = map.containsKey(fooA);
        System.out.println("testHashMap(): " + b);// false

        // false 原因: key值变了导致hashcode变了, 但是Node<K,V>中hash存储的是开始的hashcode, 并且是不可变, 所以会发现找不到了
    }

    private static void testTreeMap() {
        Map<Object, String> map = new TreeMap<>();

        map.put(new FooA(1), "fooValue");
        print(map);
        System.out.println("/////////////////////////////////////////////");
        map.put(new FooA(5), "fooValue");
        print(map);
        System.out.println("/////////////////////////////////////////////");
        map.put(new FooA(3), "fooValue");
        print(map);
    }

    private static void print(Map<Object, String> map) {
        Set<Map.Entry<Object, String>> entrySet = map.entrySet();

        for (Map.Entry<Object, String> entry : entrySet) {
            Field[] declaredFields = entry.getClass().getDeclaredFields();
            for (Field declaredField : declaredFields) {
                try {
                    declaredField.setAccessible(true);
                    System.out.println("entry  name: " + declaredField.getName() + "; value: " + declaredField.get(entry));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("---------------------------------------------");
        }
    }

    static class Foo implements Comparable<Foo> {

        Integer id;

        Integer getId() {
            return id;
        }

        void setId(Integer id) {
            this.id = id;
        }

        @Override
        public int compareTo(Foo o) {

            return (this.id - o.id);
        }

        @Override
        public int hashCode() {
            return id != null ? id.hashCode() : 0;
        }
    }

    static class FooA extends Foo {

        FooA(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            FooA fooA = (FooA) o;

            return id != null ? id.equals(fooA.id) : fooA.id == null;
        }

        @Override
        public String toString() {
            return "FooA{" +
                    "id=" + id +
                    '}';
        }
    }

    static class FooB extends Foo {

        FooB(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            FooB fooB = (FooB) o;

            return id != null ? id.equals(fooB.id) : fooB.id == null;
        }

        @Override
        public String toString() {
            return "FooB{" +
                    "id=" + id +
                    '}';
        }
    }

    static class FooC extends Foo {

        FooC(Integer id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;

            FooC fooC = (FooC) o;

            return id != null ? id.equals(fooC.id) : fooC.id == null;
        }

        @Override
        public String toString() {
            return "FooC{" +
                    "id=" + id +
                    '}';
        }
    }
}
