package com.xa.dt.mode.observer;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-10-24 11:50
 * @version: 1.0
 * @description: 抽象被观察者接口,声明了添加、删除、通知观察者方法
 */
public interface Observerable {

    public void registerObserver(Observer o);

    public void removeObserver(Observer o);

    public void notifyObserver();
}
