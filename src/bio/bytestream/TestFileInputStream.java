package bio.bytestream;

import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 死磕 FileInputStream
 */
public class TestFileInputStream{

    /**
     * 每次读取一个字节
     * read()：返回文件的下一个字节，如果达到文件的末尾，返回-1
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\Test.txt"));
        int len;
        while((len = fileInputStream.read())!= -1) {
            System.out.print((char)len);
        }
        fileInputStream.close();
    }

    /**
     * 增加一个缓冲数组
     * read(byte[] b)：返回读入缓冲数组的总字节数，如果没有更多的数据，文件的结尾已经到达，返回-1。
     * @throws IOException
     */
    @Test
    public void testRead2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\Test.txt"));
        int len;
        // 缓冲数组，用来存放每次读到的文件内容
        byte[] b = new byte[5];
        while ((len = fileInputStream.read(b))!= -1){
            System.out.println("读入缓冲数组的总字节数："+len);
            String str = new String(b,0,len);
            System.out.print(str);
        }
        fileInputStream.close();
    }

    /**
     * read(byte[] b,int off,int len)
     *      b是一个byte类型数组，当做容器来使用
     *      off是指定从数组的什么位置开始存字节
     *      len是希望读多少个
     * 其实就是把数组的一部分当做流的容器来使用。告诉容器，从什么地方开始装要装多少。
     * @throws IOException
     */
    @Test
    public void testRead3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\Test.txt"));
        byte[] byt = new byte[12];
        int start = 5;
        int maxLen = 7;
        int len = fileInputStream.read(byt, start, maxLen);
        System.out.println(len);
        for (int i = start; i < start + maxLen; i++) {
            System.out.print((char) byt[i]);
        }
        fileInputStream.close();
    }

    /**
     * skip(long n)：跳过n个字节
     * @throws IOException
     */
    @Test
    public void testRead4() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("d:\\Test.txt"));
        byte[] byt = new byte[1024];
        fileInputStream.skip(1);
        int len = fileInputStream.read(byt);
        for (int i = 0; i < len; i++) {
            System.out.print((char) byt[i]);
        }
        fileInputStream.close();
    }

    /**
     * available()：可以不受阻塞地从此输入流读取（或跳过）的估计字节数；如果到达输入流末尾，则返回 0。
     * @throws IOException
     */
    @Test
    public void testAvailable() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("d:\\Test.txt"));
        System.out.println("可读字节数："+fileInputStream.available());
        byte[] byt = new byte[fileInputStream.available()];
        int len = fileInputStream.read(byt);
        for (int i = 0; i < len; i++) {
            System.out.println((char) byt[i]);
        }
        fileInputStream.close();
    }

}
