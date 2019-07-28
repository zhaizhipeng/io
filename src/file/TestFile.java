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

        File file2 = new File(file1,"test.txt");
        if (! file2.exists()){
            System.out.println(file2.createNewFile());
        }
    }

    /**
     * 创建操作
     * @throws IOException
     */
    @Test
    public void testCreate() throws IOException {
        /**
         * 创建文件：在指定位置创建一个空文件，成功就返回true，如果已存在就不创建然后返回false
         * 指定目录必须存在，否则抛出异常
         */
        File file = new File("d:\\io\\a\\a.txt");
        boolean newFile = file.createNewFile();
        System.out.println("文件创建成功了么?" + newFile);

        /**
         * 创建文件夹：在指定位置创建目录，只会创建最后一级目录，如果上级目录不存在就抛异常
         */
        File file2 = new File("d:\\io\\b");
        boolean mkdir = file2.mkdir();
        System.out.println("文件夹创建成功了么?" + mkdir);

        /**
         * 创建文件夹：在指定位置创建目录，会创建路径中所有不存在的目录，成功返回true。如果已存在就不创建然后返回false
         */
        File file3 = new File("d:\\io\\c\\cc");
        boolean mkdirs = file3.mkdirs();
        System.out.println("文件夹创建成功了么?" + mkdirs);

        /**
         * 该方法是与平台相关的
         * 移动/重命名：重命名文件或文件夹;路径名相同，重命名文件，路径名不同，相当于重命名文件然后剪切
         * 移动/重命名成功则返回true，失败则返回false
         * 移动/重命名文件，旧文件不存在或新文件存在都会返回失败
         * 移动/重命名文件夹，旧文件夹不存在或新文件夹存在都会返回失败，重命名文件夹时，旧文件夹可以非空，剪切文件夹的时候，旧文件夹必须非空
         */
        File file4 = new File("d:\\io\\a\\aa.txt");
        System.out.println("文件重命名成功了么？" + file.renameTo(file4));
        File file5 = new File("d:\\io\\b\\bb.txt");
        System.out.println("文件移动成功了么？" + file4.renameTo(file5));
    }

    /**
     * 删除操作
     */
    @Test
    public void testDelete(){
        /**
         * delete(): 删除文件或空文件夹
         * 删除文件，文件必须存在，否则删除失败
         * 删除文件夹，文件夹必须空（也不能包含空的子文件夹），否则删除失败
         * 成功返回true，失败返回false
         */
        File file = new File("d:\\test.txt");
        System.out.println("文件删除成功了么？" + file.delete());
        File file2 = new File("d:\\io\\a");
        System.out.println("文件夹删除成功了么？" + file2.delete());

        /**
         * deleteOnExit(): JVM进程退出的时候删除文件，通常用在临时文件的删除
         */
        File file3 = new File("d:\\io\\b");
        file3.deleteOnExit();
        System.out.println("文件还存在么？"+file3.exists());
    }

}
