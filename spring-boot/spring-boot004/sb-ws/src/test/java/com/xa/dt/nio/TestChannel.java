package com.xa.dt.nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Set;

/**
 * 一、通道（Channel）:用于源节点与目标节点的连接。在java nio中负责缓冲区中数据的传输，Channel本身不存储数据，因此需要配合缓冲区进行传输
 *
 * 二、通道的主要实现类：
 * java.nio.channels.Channel 接口：
 * |--FileChannel
 * |--SocketChannel
 * |--ServerSocketChannel
 * |--DatagramChannel
 * 三、获取通道：
 * 1.java针对支持通道的类提供了getChannel()方法
 * 本地IO：
 * FileInputStream/FileOutputStream
 * RandomAccessFile
 * 网络IO：
 * Socket
 * ServerSocket
 * DatagramSocket
 * 2.在jdk 1.7中的NIO.2针对各个通道提供了一个静态方法open()
 * 3.在jdk 1.7中的NIO.2的Files工具类的newByteChannel()
 * 四、通道之间的数据传输
 * transferFrom()
 * transferTo()
 * 五、分散（Scatter）与聚集（Gather）
 * 分散读取（Scattering Reads）：将一个通道中的数据分散到多个缓冲区中
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到一个通道中
 * 六、字符集：Charset
 * 编码：字符串 ---> 字节数据
 * 解码：字节数组 ---> 字符串
 */
public class TestChannel {

    //1.利用通道完成文件的复制（非直接缓冲区）
    @Test
    public void test1() throws Exception {
        FileInputStream fis = new FileInputStream("D:\\temp\\1.jpg");
        FileOutputStream fos = new FileOutputStream("D:\\temp\\2.jpg");

        //获取通道
        FileChannel inChannel = fis.getChannel();
        FileChannel outChannel = fos.getChannel();

        //分配指定大小的缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        //将通道的数据写入缓冲区
        while (inChannel.read(byteBuffer) != -1) {
            //切换为读取数据模式
            byteBuffer.flip();
            //将缓冲区的数据写入通道
            outChannel.write(byteBuffer);
            //切换为写模式，即清空缓冲区
            byteBuffer.clear();
        }
        outChannel.close();
        inChannel.close();
        fos.close();
        fis.close();
    }

    //2.利用直接缓冲区完成文件的复制（内存映射文件）
    @Test
    public void test2() throws Exception{
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\temp\\1.jpg"), StandardOpenOption.READ);
        //CREATE_NEW存在则报错，CREATE存在则覆盖
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\temp\\3.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        //内存映射文件（物理内存中）,直接缓冲区，只有ByteBuffer支持
        MappedByteBuffer inMappedByteBuffer = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

        //物理内存中
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        //直接对缓冲区进行数据的读写操作
        byte[] dst = new byte[inMappedByteBuffer.limit()];
        inMappedByteBuffer.get(dst);
        outMappedByteBuffer.put(dst);

        inChannel.close();
        outChannel.close();
    }

    //通道之间的数据传输（直接缓冲区方式）
    @Test
    public void test3() throws Exception {
        FileChannel inChannel = FileChannel.open(Paths.get("D:\\temp\\1.jpg"), StandardOpenOption.READ);
        //CREATE_NEW存在则报错，CREATE存在则覆盖
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\temp\\4.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

//        inChannel.transferTo(0, inChannel.size(), outChannel);
        outChannel.transferFrom(inChannel, 0, inChannel.size());
        inChannel.close();
        outChannel.close();
    }

    //分散与聚集
    @Test
    public void test4() throws Exception {
        RandomAccessFile file = new RandomAccessFile("D:\\temp\\1.txt", "rw");
        //1.获取通道
        FileChannel channel1 = file.getChannel();

        //2.分配指定大小的缓冲区
        ByteBuffer bf1 = ByteBuffer.allocate(100);
        ByteBuffer bf2 = ByteBuffer.allocate(1023);

        //3.分散读取
        ByteBuffer[] dst = {bf1, bf2};
        channel1.read(dst);

        for (ByteBuffer bf:dst){
            bf.flip();
        }
        System.out.println(new String(dst[0].array(), 0, dst[0].limit()));
        System.out.println("--------------------------------------------");
        System.out.println(new String(dst[1].array(), 0, dst[1].limit()));

        //4.聚集写入
        RandomAccessFile file1 = new RandomAccessFile("D:\\temp\\2.txt", "rw");
        FileChannel channe2 = file1.getChannel();
        channe2.write(dst);
    }

    //字符集
    @Test
    public void test5() throws Exception {
        //获取所有支持的字符集
        Map<String, Charset> map = Charset.availableCharsets();
        Set<Map.Entry<String, Charset>> set = map.entrySet();
        for (Map.Entry<String, Charset> entry : set) {
            //System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        Charset cs1 = Charset.forName("GBK");
        //获取编码器
        CharsetEncoder ce = cs1.newEncoder();
        //获取解码器
        CharsetDecoder cd = cs1.newDecoder();

        CharBuffer cb = CharBuffer.allocate(1024);

        cb.put("加油，加油");
        cb.flip();

        //编码，encode获取的buffer是读模式
        ByteBuffer ebb = ce.encode(cb);
        System.out.println("-----------encode之后----------------");
        System.out.println("ebb position---" + ebb.position());
        System.out.println("ebb limit---" + ebb.limit());
        System.out.println("ebb capacity---" + ebb.capacity());
        for (int i = 0; i < ebb.limit(); i++) {
            System.out.println(ebb.get());
        }

        System.out.println("-----------读取之后----------------");
        System.out.println("ebb position---" + ebb.position());
        System.out.println("ebb limit---" + ebb.limit());
        System.out.println("ebb capacity---" + ebb.capacity());

        ebb.rewind();

        System.out.println("-------------rewind--------------");
        System.out.println("ebb position---" + ebb.position());
        System.out.println("ebb limit---" + ebb.limit());
        System.out.println("ebb capacity---" + ebb.capacity());

        //解码
        CharBuffer cb1 = cd.decode(ebb);
        System.out.println(cb1.toString());

        System.out.println("-------------decode ebb--------------");
        System.out.println("ebb position---" + ebb.position());
        System.out.println("ebb limit---" + ebb.limit());
        System.out.println("ebb capacity---" + ebb.capacity());

        System.out.println("-------------decode cb1--------------");
        System.out.println("cb1 position---" + cb1.position());
        System.out.println("cb1 limit---" + cb1.limit());
        System.out.println("cb1 capacity---" + cb1.capacity());

        Charset cs2 = Charset.forName("GBK");
        ebb.rewind();
        CharBuffer cb2 = cs2.decode(ebb);
        System.out.println("-------------decode cb2--------------");
        System.out.println("cb1 position---" + cb2.position());
        System.out.println("cb1 limit---" + cb2.limit());
        System.out.println("cb1 capacity---" + cb2.capacity());
        System.out.println(cb2.toString());
    }
}
