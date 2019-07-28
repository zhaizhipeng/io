package bio.file;
import java.io.File;
/**
 * @author dell
 * @date 2018/9/17 15:59
 */
public class FileDemo3 {
    public static void main(String args[]){
        /*
            exists()			判断文件或文件夹是否存在，存在返回true，否则返回false。
            isFile()			判断是否是一个文件，如果不存在，则返回false。
            isDirectory()		判断是否是一个目录，如果不存在，则返回false。
            isHidden()		    判读是否是一个隐藏的文件或是否是隐藏的目录。
            isAbsolute()		判断此抽象路径名是否为绝对路径。
        */
        File file = new File("E:\\Test.txt");
        System.out.println("文件存在么？" + file.exists());
        System.out.println("是文件么？" + file.isFile());
        System.out.println("是文件夹么？" + file.isDirectory());
        System.out.println("是隐藏文件么？" + file.isHidden());
        System.out.println("是绝对路径么？" + file.isAbsolute());
    }
}
