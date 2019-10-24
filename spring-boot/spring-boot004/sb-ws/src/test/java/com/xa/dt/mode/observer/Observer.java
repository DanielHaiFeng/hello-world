package com.xa.dt.mode.observer;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-10-24 11:51
 * @version: 1.0
 * @description: 抽象观察者,定义了一个update()方法，当被观察者调用notifyObservers()方法时，观察者的update()方法会被回调。
 */
public interface Observer {

    public void update(String message);
}
