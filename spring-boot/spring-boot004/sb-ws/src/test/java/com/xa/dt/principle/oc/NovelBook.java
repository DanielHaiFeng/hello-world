package com.xa.dt.principle.oc;

/**
 * @author DangTing
 * @date 2019-11-04 09:15
 * @version: 1.0
 * @description: TODO
 */

class NovelBook implements IBook {

    private String name;

    private int price;

    private String author;

    public NovelBook(String name, int price, String author) {
        this.name = name;
        this.price = price;
        this.author = author;
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
    public String getAuthor() {
        return author;
    }
}

