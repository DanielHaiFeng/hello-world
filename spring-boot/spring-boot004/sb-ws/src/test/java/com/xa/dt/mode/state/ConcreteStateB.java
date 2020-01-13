package com.xa.dt.mode.state;

/**
 * @author DangTing
 * @date 2019-11-05 17:32
 * @version: 1.0
 * @description: TODO
 */
public class ConcreteStateB implements State {

    @Override
    public void handle(String sampleParameter) {
        System.out.println("ConcreteStateB handle ：" + sampleParameter);
    }
}
