package com.bing.lan.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by 蓝兵 on 2019/4/15.
 */

public class IteratorTest {

    public static void main(String[] args) {
        List<Integer> objects = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            objects.add(i);
        }
        //for (Integer object : objects) {
        //    if (Integer.valueOf(12) == object) {
        //        objects.remove(object);
        //        System.out.println("main(): ");
        //
        //    }
        //}

        for (Iterator iterator = objects.iterator(); iterator.hasNext(); ) {
            Object next = iterator.next();
            if (Integer.valueOf(2) == next) {
                //objects.remove(next);// 不能一边迭代一边删除
                iterator.remove();
                System.out.println("main(): ");
            }
        }

        //ListIterator iterator = objects.listIterator();
        //Object next = iterator.next();
        //next = iterator.next();
        //next = iterator.next();
        //next = iterator.previous();
        //
        //iterator.remove();
        //System.out.println("main(): ");
        
    }
}
