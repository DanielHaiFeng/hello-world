package com.xa.dt.mode.mediator;

/**
 * @author DangTing
 * @date 2019-11-07 14:02
 * @version: 1.0
 * @description: 定义父同事类Colleague
 */
public class Colleague {

    protected String name;

    protected Mediator mediator;

    public Colleague(String name, Mediator mediator) {
        this.name = name;
        this.mediator = mediator;
    }
}
