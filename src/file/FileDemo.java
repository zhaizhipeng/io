package file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String args[]) throws IOException {
        File file = new File("E:\\b.txt");
        boolean newFile = file.createNewFile();
        System.out.println("文件创建成功了么？" + newFile);
    }
}
