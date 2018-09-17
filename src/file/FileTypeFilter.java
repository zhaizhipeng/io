package file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * @author dell
 * @date 2018/9/17 16:51
 */
public class FileTypeFilter implements FilenameFilter {

    private String type;

    public FileTypeFilter(String type) {
        this.type = type;
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.endsWith(type);
    }
}
