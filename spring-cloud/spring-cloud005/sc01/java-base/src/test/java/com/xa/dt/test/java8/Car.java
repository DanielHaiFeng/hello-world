package com.xa.dt.test.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Car
 * @date 2019-05-15 11:05
 * @version: 1.0
 * @description: TODO
 */
public class Car {

    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }

    public static void main(String[] args) {
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);

        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        List names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
    }
}
