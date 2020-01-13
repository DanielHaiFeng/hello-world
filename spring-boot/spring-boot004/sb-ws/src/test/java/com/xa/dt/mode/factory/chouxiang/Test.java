package com.xa.dt.mode.factory.chouxiang;

/**
 * @author DangTing
 * @date 2019-11-18 16:10
 * @version: 1.0
 * @description: 抽象工厂模式
 * 多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。 每个具体工厂类可以创建多个具体产品类的实例
 */
public class Test {

    /**
     * 优点：
     * 降低耦合
     * 符合开-闭原则
     * 符合单一职责原则
     * 不使用静态工厂方法，可以形成基于继承的等级结构
     * 缺点：
     * 难以扩展新种类产品
     * @param args
     */
    public static void main(String[] args) {
        //创建零食售卖机（具体工厂），
        FactoryA factoryA = new FactoryA();
        //获取矿泉水与面包（具体产品）
        factoryA.getProductA().intro();
        factoryA.getProductB().intro();
    }
}
