package com.xa.dt.nio;

import org.junit.Test;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingNIO2 {

    //客户端
    @Test
    public void client() throws Exception {
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        FileChannel fileChannel = FileChannel.open(Paths.get("D:\\temp\\1.jpg"), StandardOpenOption.READ);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fileChannel.read(buffer) != -1) {
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }
        //告知服务端：客户端的数据已发送完毕
        channel.shutdownOutput();

        //接收服务端的反馈
        int len = 0;
        while ((len = channel.read(buffer)) != -1) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, len));
            buffer.clear();
        }

        fileChannel.close();
        channel.close();
    }

    //服务端
    @Test
    public void server() throws Exception {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9898));
        SocketChannel socketChannel = channel.accept();
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\temp\\nio-block2.jpg"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (socketChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        //发送反馈给客户端
        buffer.put("文件已收到".getBytes());
        buffer.flip();
        socketChannel.write(buffer);

        outChannel.close();
        socketChannel.close();
        socketChannel.close();
    }
}
