package bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author dell
 * @date 2018/9/18 8:43
 */
public class FileInputStreamDemo{

    public static void main(String args[]){
        try {
            //showContent();
            //showContent2();
            //showContent3();
            //showContent4();
            showContent5();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
        read():数据的下一个字节，如果达到文件的末尾， 返回-1
     */
    private static void showContent() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\Test.txt"));
        int len = 0;
        while((len = fileInputStream.read())!= -1) {
            System.out.println((char)len);
        }
        fileInputStream.close();
    }

    /*
       read(byte[] b):读入缓冲区的总字节数，如果没有更多的数据，文件的结尾已经到达，返回-1 。
    */
    private static void showContent2() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\Test.txt"));
        int len = 0;
        byte[] b = new byte[5];
        while (len != -1){
            len = fileInputStream.read(b);
            System.out.println("length"+len);
            for (byte aB : b) {
                System.out.println((char)aB);
            }
        }
        fileInputStream.close();
    }

    /*
        read(byte[] b,int off,int len)
            b是一个byte类型数组，当做容器来使用
            off是指定从数组的什么位置开始存字节
            len是希望读多少个
            其实就是把数组的一部分当做流的容器来使用。告诉容器，从什么地方开始装要装多少。
     */
    private static void showContent3() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("E:\\Test.txt"));
        byte[] byt = new byte[1024];
        int start = 5;
        int maxLen = 7;
        int len = fileInputStream.read(byt, start, maxLen);
        System.out.println(len);
        for (int i = start; i < start + maxLen; i++) {
            System.out.print((char) byt[i]);
        }
        fileInputStream.close();
    }

    /*
        skip(long n):跳过n个字节
     */
    private static void showContent4() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\Test.txt"));
        byte[] byt = new byte[1024];
        fileInputStream.skip(2);
        int len = fileInputStream.read(byt);
        System.out.println(len);
        for (int i = 0; i < len; i++) {
            System.out.println((char) byt[i]);
        }
        fileInputStream.close();
    }

    private static void showContent5() throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File("E:\\Test.txt"));
        byte[] byt = new byte[100];
        int len = 0;
        while ((len = fileInputStream.read(byt)) != -1) {
            System.out.println(new String(byt, 0, len));
        }
    }

}
