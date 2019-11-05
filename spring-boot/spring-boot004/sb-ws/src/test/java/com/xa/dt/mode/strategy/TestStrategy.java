package com.xa.dt.mode.strategy;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-05 15:00
 * @version: 1.0
 * @description: TODO
 */
public class TestStrategy {

    /**
     * 策略模式定义
     * Strategy模式也叫策略模式是行为模式之一，它对一系列的算法加以封装，为所有算法定义一个抽象的算法接口，
     * 并通过继承该抽象算法接口对所有的算法加以封装和实现，具体的算法选择交由客户端决定（策略）。
     * Strategy模式主要用来平滑地处理算法的切换
     *
     * 策略模式的结构
     * 封装类：也叫上下文，对策略进行二次封装，目的是避免高层模块对策略的直接调用
     * 抽象策略：通常情况下为一个接口，当各个实现类中存在着重复的逻辑时，则使用抽象类来封装这部分公共的代码，此时，策略模式看上去更像是模版方法模式
     * 具体策略：具体策略角色通常由一组封装了算法的类来担任，这些类之间可以根据需要自由替换
     * @param args
     */
    public static void main(String[] args) {
        IStrategy sha1 = new SHA1Strategy();
        IStrategy md5 = new MD5Strategy();
        Context context = new Context(md5);
        context.encrypt();
    }
}
