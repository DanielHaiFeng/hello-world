package com.xa.dt.principle.isp;

/**
 * @author DangTing
 * @date 2019-11-04 09:39
 * @version: 1.0
 * @description: 接口隔离原则，客户端不应该依赖它不需要的接口；一个类对另一个类的依赖应该建立在最小的接口上
 */
public class TestIsp {

    /**
     *（1）接口要尽量小；
     *（2）接口要高内聚；
     *（3）接口设计是有限度的。
     * @param args
     */
    public static void main(String[] args) {
        PettyGirl pettyGirl = new PettyGirl("景甜");
        AbstractSearcher searcher = new Searcher(pettyGirl);
        searcher.show();
    }
}
