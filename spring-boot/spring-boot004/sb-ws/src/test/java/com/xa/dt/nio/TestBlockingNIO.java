package com.xa.dt.nio;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 一、使用NIO完成网络通信的三个核心：
 *
 * 1.通道（Channel）：负责连接
 *  java.nio.channels.Channel接口
 *     |-- SelectableChannel
 *        |-- SocketChannel
 *        |-- ServerSocketChannel
 *        |-- DatagramChannel
 *
 *        |-- Pipe.SinkChannel
 *        |-- Pipe.SourceChannel
 * 2.缓冲区（Buffer）：负责数据的存取
 *
 * 3.选择器（Selector）：是SelectableChannel的多路复用器，用于监控SelectableChannel的IO状况
 */
public class TestBlockingNIO {

    //客户端
    @Test
    public void client() throws Exception {
        //1.获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //3.读取本地文件，并发送到服务端
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\temp\\1.jpg"), StandardOpenOption.READ);
        while (inChannel.read(buffer) != -1) {
            //切换为读模式
            buffer.flip();
            channel.write(buffer);
            //切换为写模式
            buffer.clear();
        }

        //4.关闭通道
        inChannel.close();
        channel.close();
    }

    //服务端
    @Test
    public void server() throws Exception {
        //1.获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //2.绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9898));
        //3.获取客户端连接的通道
        SocketChannel socketChannel = serverSocketChannel.accept();
        //4.分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //5.接收客户端数据，并保存到本地
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\temp\\nio-block.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        while (socketChannel.read(buffer)!=-1){
            //切换为读模式
            buffer.flip();
            outChannel.write(buffer);
            //切换为写模式
            buffer.clear();
        }
        //6.关闭通道
        socketChannel.close();
        outChannel.close();
        serverSocketChannel.close();

    }
}
