package com.xa.dt.mode.principle.oc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-04 09:16
 * @version: 1.0
 * @description: 开闭原则，对扩展开放，对修改关闭
 */
public class BookStore {

    private static Logger logger = LoggerFactory.getLogger(BookStore.class);

    private final static ArrayList<IBook> sBookList = new ArrayList<IBook>();

    static {
        sBookList.add(new NovelBook("天龙八部", 3200, "金庸"));
        sBookList.add(new NovelBook("巴黎圣母院", 5600, "雨果"));
        sBookList.add(new NovelBook("悲催世界", 3500, "雨果"));
        sBookList.add(new NovelBook("金瓶梅", 4300, "兰陵笑笑生"));
        sBookList.add(new ComputerBook("Think in Java", 5800, "Bruce Eckel", "编程语言"));
    }

    public static void main(String[] args) throws IOException {
        NumberFormat format = NumberFormat.getCurrencyInstance();
        format.setMaximumFractionDigits(2);
        logger.info("JimmyZhang {}", "----书店卖出去的书籍记录如下---");
        for (IBook book : sBookList) {
            logger.info("JimmyZhang {}", "书籍名称:" + book.getName() + "\t书籍作者:" + book.getAuthor() + "\t书籍价格:" + format.format(book.getPrice() / 100.00) + "元");
        }
    }
}