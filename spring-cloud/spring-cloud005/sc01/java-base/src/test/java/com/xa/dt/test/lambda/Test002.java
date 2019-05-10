package com.xa.dt.test.lambda;

import com.xa.dt.test.lambda.fi.FI001;
import com.xa.dt.test.lambda.fi.FI002;
import org.junit.Test;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Test002
 * @date 2019-05-10 09:50
 * @version: 1.0
 * @description: TODO
 */
public class Test002 {

    @Test
    public void testLambdaFi() {
        func(new FI002() {
            @Override
            public void printWelcome(String name) {
                System.out.println(name);
            }
        });
        //使用Lambda表达式代替上面的匿名内部类
        func((name) -> System.out.println(name));
    }

    private void func(FI002 fiInstance) {
        fiInstance.printWelcome("dangting");
    }
}
