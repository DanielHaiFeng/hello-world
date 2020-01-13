package com.xa.dt.mode.state;

/**
 * @author DangTing
 * @date 2019-11-05 17:31
 * @version: 1.0
 * @description: 抽象状态类
 */
public interface State {

    /**
     * 状态对应的处理
     */
    public void handle(String sampleParameter);
}
