package bio.bytestream;

import org.junit.Test;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 死磕 FileOutputStream
 */
public class TestFileOutputStream {

    /**
     * write(int b) 每次写入一个字节
     * @throws IOException
     */
    @Test
    public void testWrite() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Test.txt");
        fileOutputStream.write('j');
        fileOutputStream.write('a');
        fileOutputStream.write('v');
        fileOutputStream.write('a');
        fileOutputStream.close();
    }

    /**
     * write(byte b[])：每次写入 b 字节数组
     * @throws IOException
     */
    @Test
    public void testWrite2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Test.txt");
        byte[] bytes = "hello java".getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    /**
     * write(byte b[], int off, int len)：每次写入 b 字节数组，off 表示开始的数组下标，len 表示写入长度
     * @throws IOException
     */
    @Test
    public void testWrite3() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Test.txt");
        byte[] bytes = "hello java".getBytes();
        fileOutputStream.write(bytes,0,3);
        fileOutputStream.close();
    }

    /**
     * FileOutputStream(String path, boolean append) ：append 为 true 表示追加写，默认为 false
     * 防止文件被重写
     * @throws IOException
     */
    @Test
    public void testWrite4() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("Test.txt",true);
        byte[] bytes = "hello java".getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

}
