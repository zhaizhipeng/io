package bio.bytestream;

import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String args[]){
        try {
           writeTxtFile();
           writeTxtFile2();
           writeTxtFile3();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
        每次写入一个字节
     */
    private static void writeTxtFile() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\test.txt");
        fileOutputStream.write('j');
        fileOutputStream.write('a');
        fileOutputStream.write('v');
        fileOutputStream.write('a');
        fileOutputStream.close();
    }

    /*
        写入字节数组
     */
    private static void writeTxtFile2() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\test.txt");
        byte[] bytes = "hello java".getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }

    /*
        防止文件被重写
     */
    private static void writeTxtFile3() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\test.txt",true);
        byte[] bytes = "hello java".getBytes();
        fileOutputStream.write(bytes);
        fileOutputStream.close();
    }
}
