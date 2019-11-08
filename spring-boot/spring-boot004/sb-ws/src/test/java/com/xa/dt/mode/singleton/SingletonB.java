package com.xa.dt.mode.singleton;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-08 14:11
 * @version: 1.0
 * @description: 饿汉模式 线程安全，比较常用，但容易产生垃圾，因为一开始就初始化
 */
public class SingletonB {

    private static SingletonB instance = new SingletonB();

    private SingletonB(){}

    public static SingletonB getInstance(){
        return instance;
    }
}
