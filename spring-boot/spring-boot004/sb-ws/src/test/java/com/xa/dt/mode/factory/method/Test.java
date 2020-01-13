package com.xa.dt.mode.factory.method;

/**
 * @author DangTing
 * @date 2019-11-18 16:01
 * @version: 1.0
 * @description: 工厂方法模式，
 * 一个抽象产品类，可以派生出多个具体产品类。一个抽象工厂类，可以派生出多个具体工厂类。每个具体工厂类只能创建一个具体产品类的实例
 */
public class Test {

    /**
     * 优点：
     * 符合开-闭原则：新增一种产品时，只需要增加相应的具体产品类和相应的工厂子类即可
     * 符合单一职责原则：每个具体工厂类只负责创建对应的产品
     * 缺点：
     * 增加了系统的复杂度：类的个数将成对增加
     * 增加了系统的抽象性和理解难度
     * 一个具体工厂只能创建一种具体产品
     * @param args
     */
    public static void main(String[] args) {
        //创建具体的工厂
        FactoryA factoryA = new FactoryA();
        //生产相对应的产品
        factoryA.getProduct().intro();

        FactoryB factoryB = new FactoryB();
        factoryB.getProduct().intro();
    }
}
