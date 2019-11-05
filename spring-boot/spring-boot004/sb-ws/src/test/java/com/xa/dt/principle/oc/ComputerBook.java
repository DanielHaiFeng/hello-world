package com.xa.dt.principle.oc;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-04 09:16
 * @version: 1.0
 * @description: TODO
 */

class ComputerBook implements IComputerBook {

    private String name;

    private int price;

    private String author;

    private String scope;

    public ComputerBook(String name, int price, String author, String scope) {
        this.name = name;
        this.price = price;
        this.author = author;
        this.scope = scope;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getScope() {
        return scope;
    }

    @Override
    public String getAuthor() {
        return author;
    }
}

