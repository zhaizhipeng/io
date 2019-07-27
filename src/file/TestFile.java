package file;

import org.junit.Test;
import java.io.File;
import java.io.IOException;

public class TestFile {

    /**
     * 构造 File 实例的三种方式
     * @throws IOException
     */
    @Test
    public void testConstructor() throws IOException {
        File file = new File("d:\\io\\");
        if (! file.exists()){
            System.out.println(file.mkdir());
        }

        File file1 = new File("d:\\io\\a\\");
        if (! file1.exists()){
            System.out.println(file1.mkdir());
        }

        File file2 = new File(file1,"a.txt");
        if (! file2.exists()){
            System.out.println(file2.createNewFile());
        }
    }

}
