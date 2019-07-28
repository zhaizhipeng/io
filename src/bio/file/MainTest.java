package bio.file;

import bio.util.FileUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dell
 * @date 2018/9/17 18:07
 */
public class MainTest {
    public static void  main(String args[]){
        File file = new File("E:\\aa");
        System.out.println(Arrays.toString(file.list()));
        List<File> filesList = new ArrayList<File>();
        List<File> dirsList = new ArrayList<File>();
        File[] files = file.listFiles();
        for (File f : files != null ? files : new File[0]){
            if (f.isFile()){
                filesList.add(f);
            }
            if (f.isDirectory()){
                dirsList.add(f);
            }
        }
        // 分别显示文件名与文件夹名
        System.out.println("子文件：");
        for (File aFileList : filesList) {
            System.out.println("\t" + aFileList.getName());
        }
        System.out.println("子目录：");
        for (File aDirList : dirsList) {
            System.out.println("\t" + aDirList.getName());
        }

        // 自定义筛选指定类型的文件
        System.out.println("txt类型文件：");
        for (File aFile : filesList) {
            if (aFile.getName().endsWith("txt")){
                System.out.println("\t" + aFile.getName());
            }
        }

        // 自定义文件类型筛选器
        FileTypeFilter fileFilter = new FileTypeFilter(".txt");
        File myFile = new File("E:\\aa");
        System.out.println("txt类型文件：");
        for (File aFile : FileUtil.fileFilter(myFile, fileFilter)){
            System.out.println("\t" + aFile.getName());
        }

    }

}
