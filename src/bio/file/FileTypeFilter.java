package bio.file;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 类型过滤
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
