package com.xa.dt.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author DangTing
 * @classname ColorGenerator
 * @date 2019-04-16 15:09
 * @version: 1.0
 * @description: TODO
 */
public class ColorGenerator implements GenericInterface<String> {

    private static final String[] colors = {"red", "white", "black"};

    @Override
    public String next() {
        Random random = new Random();
        return colors[random.nextInt(3)];
    }

    public <T> boolean isContains(T t, List<T> list) {
        for (T tmp : list) {
            if (t.equals(tmp)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ColorGenerator generator = new ColorGenerator();
        System.out.println(generator.next());
        List<Integer> ints = new ArrayList<Integer>();
        ints.add(12);
        ints.add(13);
        ints.add(14);
        ints.add(15);
        System.out.println(generator.isContains(8, ints));

        List<String> sts = new ArrayList<String>();
        sts.add(new String("aaa"));
        sts.add(new String("bbb"));
        sts.add(new String("ccc"));
        System.out.println(generator.isContains("aaa", sts));

        String str = "aaa";
        System.out.println("aaa" == str);
    }
}
