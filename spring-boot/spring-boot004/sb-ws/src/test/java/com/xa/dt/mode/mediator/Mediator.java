package com.xa.dt.mode.mediator;

/**
 * @author DangTing
 * @date 2019-11-07 14:01
 * @version: 1.0
 * @description: 定义抽象Mediator,可以与同事们进行联络
 */
public abstract class Mediator {

    public abstract void contact(String content,Colleague coll);
}
