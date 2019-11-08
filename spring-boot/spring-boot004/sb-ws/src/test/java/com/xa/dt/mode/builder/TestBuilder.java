package com.xa.dt.mode.builder;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-07 17:05
 * @version: 1.0
 * @description: 生成器模式，简化复杂对象的生成，旅游计划项目，对于用户来说只关心旅游日期和开始时间，可以自动展示出所有计划即可
 */
public class TestBuilder {

    /**
     * 将复杂对象的创建过程封装起来
     * 允许对象通过几个步骤来创建，并且可以改变过程（工厂模式只有一个步骤）
     * 只需指定具体生成器就能生成特定对象，隐藏类的内部结构
     * 对象的实现可以被替换
     * @param args
     */
    public static void main(String[] args) {
        Director mDirector = new Director(new Builder4d("2015-12-29"));
        mDirector.construct();
        mDirector.setBuilder(new Builder3d("2015-8-30"));
        mDirector.construct();
    }
}
