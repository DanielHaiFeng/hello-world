package com.xa.dt.mode.builder;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-07 16:39
 * @version: 1.0
 * @description: TODO
 */
public class Builder4d extends AbsBuilder {

    public Builder4d(String std) {
        super(std);
    }

    @Override
    public void buildDay(int i) {
        mVacation.setVacationDay(i);
    }

    @Override
    public void addHotel(String hotel) {
        mVacation.setHotel(hotel);
    }

    @Override
    public void addTicket(String ticket) {
        mVacation.addTicket(ticket);
    }

    @Override
    public void addEvent(String event) {
        mVacation.addEvent(event);
    }

    @Override
    public void buildvacation() {
        addTicket("Plane Ticket");
        addEvent("Fly to Destination");
        addEvent("Supper");
        addHotel("Hilton");

        mVacation.addDay();
        addTicket("Zoo Ticket");
        addEvent("Bus to Zoo");
        addEvent("Feed animals");
        addHotel("Hilton");

        mVacation.addDay();
        addTicket("Beach");
        addEvent("Swimming");
        addHotel("Home inn");

        mVacation.addDay();
        addTicket("Plane Ticket");
        addEvent("Fly to Home");
    }
}
