package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-05 17:31
 * @version: 1.0
 * @description: TODO
 */
public class ConcreteStateA implements State {

    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateA handle ：" + sampleParameter);
    }
}
