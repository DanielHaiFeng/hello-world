package com.xa.dt.mode.singleton;

/**
 * @author DangTing
 * @date 2019-11-08 14:10
 * @version: 1.0
 * @description: 懒汉模式 线程不安全，延迟初始化，严格意义上不是单例模式
 */
public class SingletonA {

    private static SingletonA instance;

    private SingletonA() {
    }

    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
}
