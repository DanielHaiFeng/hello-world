package com.xa.dt.mode.strategy;

/**
 * @author DangTing
 * @date 2019-11-05 14:59
 * @version: 1.0
 * @description: TODO
 */
public class Context {

    private IStrategy strategy;

    public Context(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void encrypt() {
        this.strategy.encrypt();
    }
}
