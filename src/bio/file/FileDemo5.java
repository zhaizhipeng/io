package bio.file;

import java.io.File;
import java.util.Arrays;

/**
 * @author dell
 * @date 2018/9/17 16:30
 */
public class FileDemo5 {
    public  static void main(String args[]){
        /*
         staic File[] listRoots()	        列出所有的根目录（Window中就是所有系统的盘符）
         list()		                        返回目录下的文件或者目录名，包含隐藏文件，对于文件这样操作会返回null。
         list(FilenameFilter filter)	    返回指定当前目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
         listFiles()		                返回目录下的文件或者目录对象（File类实例），包含隐藏文件，对于文件这样操作会返回null。
         listFiles(FilenameFilter filter)	返回指定当前目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
        */
        System.out.println("根目录：" + Arrays.toString(File.listRoots()));
        File file = new File("E:\\aa");
        System.out.println(Arrays.toString(file.list()));
        FileTypeFilter fileTypeFilter = new FileTypeFilter(".txt");
        System.out.println(Arrays.toString(file.list(fileTypeFilter)));
        System.out.println(Arrays.toString(file.listFiles()));
        System.out.println(Arrays.toString(file.listFiles(fileTypeFilter)));
    }
}
