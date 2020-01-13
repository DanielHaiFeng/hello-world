package com.xa.dt.mode.singleton;

/**
 * @author DangTing
 * @date 2019-11-08 14:13
 * @version: 1.0
 * @description: 双重锁模式 线程安全，延迟初始化。这种方式采用双锁机制，安全且在多线程情况下能保持高性能
 */
public class SingletonC {

    private volatile static SingletonC instance;

    private SingletonC() {
    }

    public static SingletonC getInstance() {
        if (instance == null) {
            synchronized (SingletonC.class) {
                if (instance == null) {
                    instance = new SingletonC();
                }
            }
        }
        return instance;
    }
}
