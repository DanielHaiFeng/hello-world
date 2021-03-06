package com.xa.dt.mode.factory.method;

/**
 * @ FactoryB.java
 * @具体工厂B
 * 负责具体的产品B生产
 */
public class FactoryB extends Factory {

    @Override
    Product getProduct() {
        return new ProductB();
    }
}
