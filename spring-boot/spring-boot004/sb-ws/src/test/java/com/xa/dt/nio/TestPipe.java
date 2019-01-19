package com.xa.dt.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 可以利用Pipe来实现线程之间的单向通信
 */
public class TestPipe {

    @Test
    public void test() throws IOException {
        //1.获取管道
        Pipe pipe = Pipe.open();

        //2.获取发送数据的channel 即sink channel
        Pipe.SinkChannel sinkChannel = pipe.sink();

        //3.将缓冲区中的数据写入sink channel
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        buffer.put("通过单向管道发送数据".getBytes());
        buffer.flip();
        sinkChannel.write(buffer);
        buffer.clear();

        //4.通过读取channel即source channel获得sink channel发送的数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        sourceChannel.read(buffer);
        buffer.flip();
        System.out.println(new String(buffer.array(), 0, buffer.limit()));

        sourceChannel.close();
        sinkChannel.close();
    }
}
