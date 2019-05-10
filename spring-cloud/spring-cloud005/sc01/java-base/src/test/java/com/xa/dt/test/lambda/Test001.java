package com.xa.dt.test.lambda;

import com.xa.dt.test.lambda.fi.FI001;
import org.junit.Test;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname Test001
 * @date 2019-05-10 09:44
 * @version: 1.0
 * @description: TODO
 */
public class Test001 {

    @Test
    public void testLambda() {
        //普通方式
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello World!");
            }
        });
        //lambda方式
        new Thread(() -> System.out.println("test"));
    }

    @Test
    public void testLambdaFi(){
        func(new FI001() {
            @Override
            public void test() {
                System.out.println("Hello World!");
            }
        });
        //使用Lambda表达式代替上面的匿名内部类
        func(() -> System.out.println("Hello World"));
    }

    private void func(FI001 fiInstance) {
        fiInstance.test();
    }
}
