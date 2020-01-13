package com.xa.dt.generic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author DangTing
 * @classname TestLambda
 * @date 2019-08-16 16:47
 * @version: 1.0
 * @description: TODO
 */
@Slf4j
public class TestLambda {

    @Test
    public void testLambdaThread() {
        new Thread(() -> {
            log.info("In Java8, Lambda expression rocks !!");
        }).start();
    }

    @Test
    public void testLambdaList() {
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
    }

    @Test
    public void testLambdaIt() {
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp");

        System.out.println("Languages which starts with J :");
        Predicate<String> pre = (str) -> {
            return str.startsWith("J");
        };
        filter(languages, pre);

        System.out.println("Languages which ends with a ");
        pre = (str) -> {
            return str.endsWith("a");
        };
        filter(languages, pre);

        System.out.println("Print all languages :");
        filter(languages, (str)->true);

        System.out.println("Print no language : ");
        filter(languages, (str)->false);

        System.out.println("Print language whose length greater than 4:");
        pre = (str) -> {
            return str.length() > 4;
        };
        filter(languages, pre);
    }

    private void filter(List<String> names, Predicate condition) {
        for (String name : names) {
            if (condition.test(name)) {
                System.out.println(name + " ");
            }
        }
    }

    @Test
    public void testMapReduceLambda() {
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        Optional<Double> reduce = costBeforeTax.stream().map((cost) -> cost * 0.5).reduce(Double::max);
        System.out.println(reduce.get());
        costBeforeTax.stream().map((cost) -> cost + 0.12 * cost).forEach(System.out::println);
    }
}
