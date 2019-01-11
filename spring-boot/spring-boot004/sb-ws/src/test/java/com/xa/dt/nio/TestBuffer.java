package com.xa.dt.nio;

import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * 一、缓冲区（Buffer）：在java-nio中负责数据的存取。缓冲区就是数据。用于存储不同数据类型的数据
 * 
 * 根据数据类型不同（Boolean除外），提供了相应类型的缓冲区：
 * ByteBuffer
 * CharBuffer
 * ShortBuffer
 * IntBuffer
 * LongBuffer
 * FloatBuffer
 * DoubleBuffer
 * 
 * 上述缓冲区的管理方式几乎一致，通过allocate()获取缓冲区
 * 
 * 二、缓冲区存取数据的两个核心方法：
 * put()：存入数据到缓冲区中
 * get()：获取缓冲区中的数据
 * 
 * 三、缓冲区中的四个核心属性
 * capacity：缓冲区最大存储数据的容量，一旦声明不能改变
 * limit：界限，表示缓冲区中可以操作数据的大小，limit后的数据不能进行读写
 * position：位置，表示缓冲区中正在操作数据的位置
 * 
 * mark标记，表示记录当前position的位置，可以通过reset()恢复到mark的位置
 * 
 * 0 <= mark <= position <= limit <= capacity
 *
 * 四、直接缓冲区和非直接缓冲区
 * 非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在jvm的内存中
 * 直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区简历在物理内存中，在某种情况下可以提高效率。程序通过物理内存映射文件与磁盘交换数据
 */
public class TestBuffer {

    @Test
    public void test1() {
        String str = "abcde";

        //1. 分配一个指定大小的缓冲区，刚分配之后，缓冲区是写模式
        ByteBuffer buf = ByteBuffer.allocate(1024);

        System.out.println("--------------------allocate---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //2.利用put()方法存入数据到缓冲区中
        buf.put(str.getBytes());

        System.out.println("--------------------put---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //3.利用flip()切换到读数据模式
        buf.flip();

        System.out.println("--------------------flip---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //4.利用get()读取缓冲区数据
        byte[] dst = new byte[buf.limit()];
        buf.get(dst);
        System.out.println(new String(dst, 0, dst.length));

        System.out.println("--------------------get---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //5.rewind() 可重复读数据
        buf.rewind();

        System.out.println("--------------------rewind---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());

        //6.clear() 清空缓冲区，position归0，limit 1024，capacity 1024，但数据依然存在，只是处于'被遗忘'状态，也相当于切换回写输入模式，但是要小心被遗忘的数据被不小心覆盖掉
        buf.clear();

        System.out.println("--------------------clear---------------------");
        System.out.println(buf.position());
        System.out.println(buf.limit());
        System.out.println(buf.capacity());
        //clear()之后数据依然存在
        System.out.println((char) buf.get());
        //get()方法默认获取当前position的数据，get之后position加1
        System.out.println(buf.position());
    }

    @Test
    public void test2() {
        String str = "abcde";
        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(str.getBytes());
        //切换到读模式
        buf.flip();

        byte[] dst = new byte[buf.limit()];

        buf.get(dst, 0, 2);
        System.out.println(new String(dst, 0, 2));
        System.out.println(buf.position());
        //mark() 标记
        buf.mark();

        buf.get(dst, 2, 2);
        System.out.println(new String(dst, 2, 2));
        System.out.println(buf.position());

        buf.reset();
        System.out.println(buf.position());

        //判断缓冲区中是否还有剩余数据，即position是否小于limit
        if (buf.hasRemaining()) {
            //获取缓冲区中可以操作的数据个数
            System.out.println(buf.remaining());
        }
    }

    @Test
    public void test3() {
        //分配直接缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        //判断是否为直接缓冲区
        System.out.println(byteBuffer.isDirect());
    }
}
