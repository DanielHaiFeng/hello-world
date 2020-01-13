package com.xa.dt.mode.builder;

/**
 * @author DangTing
 * @date 2019-11-07 16:38
 * @version: 1.0
 * @description: 生成器模式，定义抽象生成器，定义基本的抽象方法
 */
public abstract class AbsBuilder {

    public Vacation mVacation;

    public AbsBuilder(String std) {
        mVacation = new Vacation(std);
    }

    public abstract void buildvacation();

    public abstract void buildDay(int i);

    public abstract void addHotel(String hotel);

    public abstract void addTicket(String ticket);

    public abstract void addEvent(String tvent);

    public Vacation getVacation() {
        return mVacation;
    }
}
