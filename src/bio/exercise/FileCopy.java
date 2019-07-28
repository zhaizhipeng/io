package bio.exercise;

import org.junit.Test;

import java.io.*;

/**
 * 文件拷贝
 */
public class FileCopy {

    @Test
    public void testCopyFile() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("d:\\消愁-毛不易.mp3"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\消愁-毛不易（副本）.mp3"));
        byte[] bytes = new byte[2048];
        int len;
        System.out.println("可读的字节总数：" + fileInputStream.available());
        int write = 0;
        while ((len = fileInputStream.read(bytes)) != -1){
            write = write + len;
            fileOutputStream.write(bytes,0, len);
        }
        System.out.println("写入的字节总数：" + write);
        fileInputStream.close();
        fileOutputStream.close();
    }

    /**
     *  BufferedInputStream比FileInputStream多了一个缓冲区，执行read时先从缓冲区读取，当缓冲区数据读完时再把缓冲区填满。
     *  因此，当每次读取的数据量很小时，FileInputStream每次都是从硬盘读入，而BufferedInputStream大部分是从缓冲区读入。
     *  读取内存速度比读取硬盘速度快得多，因此BufferedInputStream效率高。
     *  BufferedInputStream的默认缓冲区大小是8192字节。当每次读取数据量接近或远超这个值时，两者效率就没有明显差别了。
     *  BufferedOutputStream和FileOutputStream同理，差异更明显一些。
     * @throws IOException
     */
    @Test
    public void testCopyFileWithBuffer() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("d:\\消愁-毛不易.mp3"));
        FileOutputStream fileOutputStream = new FileOutputStream(new File("d:\\消愁-毛不易（副本）.mp3"));
        // 使用缓冲流
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        // 读取和写入信息
        int len = 0;
        byte[] bytes = new byte[100];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0, len);
        }
        bis.close();
        bos.close();
    }

}
