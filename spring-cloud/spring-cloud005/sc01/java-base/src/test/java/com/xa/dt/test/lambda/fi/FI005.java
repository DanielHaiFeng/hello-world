package com.xa.dt.test.lambda.fi;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @classname FI005
 * @date 2019-05-14 09:16
 * @version: 1.0
 * @description: TODO
 */
@FunctionalInterface
public interface FI005 {

    //函数式接口中可以包含用default修饰的具体方法
    default void doSomeWork(String msg){
        System.out.println("default method " + msg);
    }

    //函数式接口中可以包含静态方法，因为静态方法不能是抽象方法，是一个已经实现了的方法
    static void printHello(String message){
        System.out.println("hello " + message);
    }

    //函数式接口中可以包含Object对象的抽象方法
    @Override
    boolean equals(Object object);

    void sayMessage(String message);
}
