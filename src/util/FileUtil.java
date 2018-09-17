package util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dell
 * @date 2018/9/17 17:20
 */
public class FileUtil {

    public static String[] filter(File file, FilenameFilter filter){
        return file.list(filter);
    }

    public static File[] fileFilter(File file, FilenameFilter filter){
        return file.listFiles(filter);
    }
}
