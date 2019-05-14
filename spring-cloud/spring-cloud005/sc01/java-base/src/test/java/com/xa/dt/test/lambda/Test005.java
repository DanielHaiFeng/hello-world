package com.xa.dt.test.lambda;

import com.xa.dt.test.lambda.fi.FI005;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Test005
 * @date 2019-05-14 09:17
 * @version: 1.0
 * @description: TODO
 */
public class Test005 {

    public static void main(String[] args) {
        FI005 fi005 = message -> System.out.println(message);
        fi005.sayMessage("hello,dangting");
    }
}
