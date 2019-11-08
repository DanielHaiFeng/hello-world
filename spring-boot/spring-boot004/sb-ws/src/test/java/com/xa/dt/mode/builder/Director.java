package com.xa.dt.mode.builder;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-07 16:41
 * @version: 1.0
 * @description: 生成器模式，引导者对象
 */
public class Director {

    private AbsBuilder builder;

    public Director(AbsBuilder builder) {
        this.builder = builder;
    }

    public void setBuilder(AbsBuilder builder) {
        this.builder = builder;
    }

    public void construct() {
        builder.buildvacation();
        builder.getVacation().showInfo();
    }
}
