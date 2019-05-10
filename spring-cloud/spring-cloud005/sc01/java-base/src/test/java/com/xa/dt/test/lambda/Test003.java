package com.xa.dt.test.lambda;

import com.xa.dt.test.lambda.fi.FI003;
import org.junit.Test;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Test003
 * @date 2019-05-10 10:23
 * @version: 1.0
 * @description: TODO
 */
public class Test003 {

    @Test
    public void testLambda() {
        func(new FI003<Integer>() {
            @Override
            public void printParam(Integer param) {
                System.out.println("hello world, " + param);
            }
        });
        func((Integer x) -> System.out.println("hello world, " + x));
    }

    private void func(FI003<Integer> fiInstance) {
        fiInstance.printParam(1234);
    }
}
