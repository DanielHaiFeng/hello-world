package com.xa.dt.mode.factory.simple;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-18 15:53
 * @version: 1.0
 * @description: 简单工厂模式，一个抽象产品类，可以派生出多个具体产品类。一个具体工厂类，
 *               通过往此工厂的static方法中传入不同参数，产出不同的具体产品类实例
 */
public class Test {

    /**
     * 优点：将创建使用工作分开，不必关心类对象如何创建，实现了解耦；
     * 违背“开放 - 关闭原则”，一旦添加新产品就不得不修改工厂类的逻辑，这样就会造成工厂逻辑过于复杂。
     * @param args
     */
    public static void main(String[] args) {
        //创建具体的工厂
        Factory factory = new Factory();
        //根据传入的参数生产不同的产品实例
        //(按下不同的按钮，获取饮料)
        Product A = Factory.getProduct("A");
        A.intro();
        Product B = Factory.getProduct("B");
        B.intro();
        Product C = Factory.getProduct("C");
        C.intro();
    }
}
