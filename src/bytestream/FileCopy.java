package bytestream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {

    public static void main(String args[]) throws IOException {
        copyFile(new File("E:\\a.txt"),new File("E:\\b.txt"));
        copyFile(new File("E:\\a.jpg"),new File("E:\\b.jpg"));
        copyFile(new File("E:\\薛之谦 - 演员.mp3"),new File("E:\\演员.mp3"));
        copyFilePerfect(new File("E:\\a.jpg"),new File("E:\\b.jpg"));
    }

    /*
        复制的文件大小会大于源文件
     */
    private static void copyFile(File orginFile,File destFile) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(orginFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile);
        byte[] bytes = new byte[52];
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
        byte[] bytes = new byte[1024];
        int len = 0;
        while ((len = fileInputStream.read(bytes)) != -1){
            fileOutputStream.write(bytes,0, len);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

}
