package com.xa.dt.mode.adapter;

/**
 * @author DangTing
 * @date 2019-11-08 15:00
 * @version: 1.0
 * @description: 适配器（Adapter）：连接目标和源的中间对象，相当于插头转换器
 */
public class Adapter extends Adaptee implements Target {
    @Override
    public void method2() {
        System.out.println("method 2");
    }
}

class AdapterTest {

    /**
     * 适配器模式是一种结构型设计模式。适配器模式的思想是：把一个类的接口变换成客户端所期待的另一种接口，
     * 从而使原本因接口不匹配而无法在一起工作的两个类能够在一起工作。
     * 用电器来打个比喻：有一个电器的插头是三脚的，而现有的插座是两孔的，要使插头插上插座，我们需要一个插头转换器，这个转换器即是适配器
     *
     * @param args
     */
    public static void main(String[] args) {
        Adapter adapter = new Adapter();
        adapter.method1();
        adapter.method2();
    }
}