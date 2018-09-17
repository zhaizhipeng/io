package file;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String args[]) throws IOException {
        /* 1、创建文件：在指定位置创建一个空文件，成功就返回true，如果已存在就不创建然后返回false */
        File file = new File("E:\\test.txt");
        boolean newFile = file.createNewFile();
        System.out.println("文件创建成功了么?" + newFile);
        /* 2、创建文件夹：在指定位置创建目录，只会创建最后一级目录，如果上级目录不存在就抛异常 */
        File file2 = new File("E:\\a\\b");
        boolean mkdir = file2.mkdir();
        System.out.println("文件夹创建成功了么?" + mkdir);
        /* 3、创建文件夹：在指定位置创建目录，会创建路径中所有不存在的目录，成功返回true。如果已存在就不创建然后返回false */
        File file3 = new File("E:\\aa\\bb");
        boolean mkdirs = file3.mkdirs();
        System.out.println("文件夹创建成功了么?" + mkdirs);
        /* 4、移动/重命名：重命名文件或文件夹;也可以操作非空的文件夹,文件不同时相当于文件的剪切,剪切时候不能操作非空的文件夹。移动/重命名成功则返回true，失败则返回false */
        File file4 = new File("E:\\Test.txt");
        System.out.println("文件重命名成功了么？" + file.renameTo(file4));
        File file5 = new File("E:\\aa\\cc");
        System.out.println("文件夹重命名成功了么？" + file3.renameTo(file5));
    }
}
