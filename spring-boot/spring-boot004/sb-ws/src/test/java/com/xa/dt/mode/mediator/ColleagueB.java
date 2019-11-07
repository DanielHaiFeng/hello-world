package com.xa.dt.mode.mediator;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-07 14:04
 * @version: 1.0
 * @description: 定义具体ColleagueB
 */
public class ColleagueB extends Colleague {

    public ColleagueB(String name, Mediator mediator) {
        super(name, mediator);
    }

    public void getMessage(String message) {
        System.out.println("同事B" + name + "获得信息" + message);
    }

    //同事B与中介者通信
    public void contact(String message) {
        mediator.contact(message, this);
    }
}
