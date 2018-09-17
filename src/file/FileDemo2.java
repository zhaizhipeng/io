package file;

import java.io.File;

/**
 * @author dell
 * @date 2018/9/17 14:35
 */
public class FileDemo2 {
    public static void main(String args[]){
        /*
         delete(): 删除文件或空文件夹：删除文件或一个空文件夹，如果是文件夹且不为空，则不能删除，成功返回true，失败返回false
         deleteOnExit(): JVM进程退出的时候删除文件,通常用在临时文件的删除
        */
        File file = new File("E:\\Test.txt");
        System.out.println("文件删除成功了么？" + file.delete());
        File file2 = new File("E:\\aa");
        System.out.println("文件夹删除成功了么？" + file2.delete());
        File file3 = new File("E:\\bb");
        file3.deleteOnExit();
        System.out.println("文件夹删除成功了么？" + file3.delete());
    }
}
