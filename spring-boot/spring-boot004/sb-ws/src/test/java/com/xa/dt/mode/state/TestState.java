package com.xa.dt.mode.state;

/**
 * @author DangTing
 * @date 2019-11-05 17:32
 * @version: 1.0
 * @description: 状态模式测试类
 */
public class TestState {

    /**
     * 状态模式，又称状态对象模式（Pattern of Objects for States），状态模式是对象的行为模式
     * 状态模式允许一个对象在其内部状态改变的时候改变其行为。这个对象看上去就像是改变了它的类一样
     *
     * 环境(Context)角色，也成上下文：定义客户端所感兴趣的接口，并且保留一个具体状态类的实例。这个具体状态类的实例给出此环境对象的现有状态
     * 抽象状态(State)角色：定义一个接口，用以封装环境（Context）对象的一个特定的状态所对应的行为
     * 具体状态(ConcreteState)角色：每一个具体状态类都实现了环境（Context）的一个状态所对应的行为
     * @param args
     */
    public static void main(String[] args) {
        //创建状态
        State state = new ConcreteStateB();
        //创建环境
        Context context = new Context();
        //将状态设置到环境中
        context.setState(state);
        //请求
        context.request("test");
    }
}
