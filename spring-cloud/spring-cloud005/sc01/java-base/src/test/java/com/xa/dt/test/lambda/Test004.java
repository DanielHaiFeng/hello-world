package com.xa.dt.test.lambda;

import com.xa.dt.test.lambda.fi.FI004;
import org.junit.Test;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Test004
 * @date 2019-05-10 11:10
 * @version: 1.0
 * @description: TODO
 */
public class Test004 {

    @Test
    public void testLambda() {
        //使用Lambda表达式代替匿名内部类
        func((Integer x) -> {
            if (x > 10) {
                return true;
            } else {
                return false;
            }
        });
    }

    private void func(FI004<Integer> fiInstance) {
        int x = 60;
        System.out.println(x + "是否大于10:" + fiInstance.test(x));
    }
}
