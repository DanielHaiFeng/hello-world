package com.xa.dt.principle.lsp;

/**
 * @author DangTing
 * @date 2019-11-01 17:47
 * @version: 1.0
 * @description: 里氏代换原则，子类可以出现的地方，基类就可以出现
 */
public class TestLsp {

    public static void main(String[] args) {
        Snake spirit = new WhiteSnake();
        spirit.say();
    }
}
