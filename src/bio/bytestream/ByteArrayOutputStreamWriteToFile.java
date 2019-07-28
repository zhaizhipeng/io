package bio.bytestream;

import org.junit.Test;

import java.io.*;

/**
 * 利用 ByteArrayOutputStream 向文件输出内容
 */
public class ByteArrayOutputStreamWriteToFile {

    @Test
    public void testWrite() throws IOException {
        String test = "hello world!";
        byte[] buf = test.getBytes();
        ByteArrayOutputStream byteArrayInputStream = new ByteArrayOutputStream();
        byteArrayInputStream.write(buf);
        byteArrayInputStream.writeTo(new FileOutputStream(new File("Test.txt")));
        byteArrayInputStream.close();
    }

}
