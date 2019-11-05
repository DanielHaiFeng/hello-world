package com.xa.dt.principle.lod;

import java.util.ArrayList;
import java.util.List;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-04 09:31
 * @version: 1.0
 * @description: 迪米特法则，也称为最少知识原则，一个对象应该对其他对象有最少的了解
 */
public class TestLod {

    /**
     *（1）在类的划分上，应当尽量创建松耦合的类，类之间的耦合度越低，就越有利于复用，一个处在松耦合中的类一旦被修改，不会对关联的类造成太大波及；
     *（2）在类的结构设计上，每一个类都应当尽量降低其成员变量和成员函数的访问权限；
     *（3）在类的设计上，只要有可能，一个类型应当设计成不变类；
     *（4）在对其他类的引用上，一个对象对其他对象的引用应当降到最低。
     * @param args
     */
    public static void main(String[] args) {
        List<Girl> listGirls = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            listGirls.add(new Girl());
        }
        Teacher teacher = new Teacher();
        GroupLeader groupLeader = new GroupLeader(listGirls);
        teacher.command(groupLeader);
    }
}
