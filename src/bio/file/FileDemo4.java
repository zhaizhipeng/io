package bio.file;

import java.io.File;

/**
 * @author dell
 * @date 2018/9/17 16:18
 */
public class FileDemo4 {
    public static void main(String args[]){
        /*
        getName()			获取文件或文件夹的名称，不包含上级路径。
        getPath()       	返回绝对路径，可以是相对路径，但是目录要指定
        getAbsolutePath()   获取文件的绝对路径，与文件是否存在没关系
        length()		    获取文件的大小（字节数），如果文件不存在则返回0L，如果是文件夹也返回0L
        getParent()		    返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null
        lastModified()	    获取最后一次被修改的时间
        */
        File file = new File("E:\\aa\\Test.txt");
        System.out.println("文件名：" + file.getName());
        System.out.println("路径：" + file.getPath());
        System.out.println("绝对路径：" + file.getAbsolutePath());
        System.out.println("文件大小：" + file.length());
        System.out.println("父目录路径名字符串：" + file.getParent());
        System.out.println("最近修改时间：" + file.lastModified());
    }
}
