package bytestream;

import java.io.*;

public class FileCopy {

    /*
        BufferedInputStream比FileInputStream多了一个缓冲区，执行read时先从缓冲区读取，当缓冲区数据读完时再把缓冲区填满。
        因此，当每次读取的数据量很小时，FileInputStream每次都是从硬盘读入，而BufferedInputStream大部分是从缓冲区读入。
        读取内存速度比读取硬盘速度快得多，因此BufferedInputStream效率高。
        BufferedInputStream的默认缓冲区大小是8192字节。当每次读取数据量接近或远超这个值时，两者效率就没有明显差别了。
        BufferedOutputStream和FileOutputStream同理，差异更明显一些。
     */
    public static void main(String args[]) throws IOException {
        //copyFile(new File("E:\\a.txt"),new File("E:\\b.txt"));
        //copyFile(new File("E:\\a.jpg"),new File("E:\\b.jpg"));
        //copyFile(new File("E:\\薛之谦 - 演员.mp3"),new File("E:\\演员.mp3"));

        long time1=System.currentTimeMillis();
        copyFilePerfect(new File("E:\\a.exe"),new File("E:\\b.exe"));
        long time2=System.currentTimeMillis();
        System.out.println("直接复制用时："+(time2-time1)+"毫秒");

        long time3=System.currentTimeMillis();
        copyFileWithBuffer("E:\\a.exe","E:\\c.exe");
        long time4=System.currentTimeMillis();
        System.out.println("复制用时："+(time4-time3)+"毫秒");
    }

    /*
        复制的文件大小会大于源文件
     */
    private static void copyFile(File orginFile,File destFile) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(orginFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        byte[] bytes = new byte[1024];
        while (fileInputStream.read(bytes) != -1){
            System.out.println(bytes.length);
            fileOutputStream.write(bytes);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void copyFilePerfect(File orginFile,File destFile) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(orginFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        byte[] bytes = new byte[2048];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0, len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    private static void copyFileWithBuffer(String srcPath, String destPath)
            throws IOException {

        FileInputStream fileInputStream = new FileInputStream(srcPath);
        FileOutputStream fileOutputStream = new FileOutputStream(destPath);
        // 使用缓冲流
        BufferedInputStream bis = new BufferedInputStream(fileInputStream);
        BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);
        // 读取和写入信息
        int len = 0;
        byte[] bytes = new byte[100];
        while ((len = bis.read(bytes)) != -1) {
            bos.write(bytes,0,len);
        }
        // 关闭流
        bis.close();
        bos.close();
    }

}
