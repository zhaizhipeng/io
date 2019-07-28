package bio.file;

import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * 死磕 File
 */
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

    /**
     * 判断操作
     */
    @Test
    public void testJudge(){
        /**
         * exists()			判断文件或文件夹是否存在，存在返回true，否则返回false
         * isFile()			判断是否是一个文件，如果不存在，则返回false
         * isDirectory()    判断是否是一个目录，如果不存在，则返回false
         * isHidden()		判读是否是一个隐藏的文件或是否是隐藏的目录
         * isAbsolute()		判断此抽象路径名是否为绝对路径
         */
        File file = new File("d:\\Test.txt");
        System.out.println("文件存在么？" + file.exists());
        System.out.println("是文件么？" + file.isFile());
        System.out.println("是文件夹么？" + file.isDirectory());
        System.out.println("是隐藏文件么？" + file.isHidden());
        System.out.println("是绝对路径么？" + file.isAbsolute());
    }

    /**
     * 获取操作
     */
    @Test
    public void testGet(){
        /**
         * getName()		    获取文件或文件夹的名称，不包含上级路径。
         * getPath()       	    返回绝对路径，可以是相对路径，但是目录要指定
         * getAbsolutePath()    获取文件的绝对路径，与文件是否存在没关系
         * length()		        获取文件的大小（字节数），如果文件不存在则返回0L，如果是文件夹也返回0L
         * getParent()		    返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null
         * lastModified()	    获取最后一次被修改的时间
         */
        File file = new File("d:\\Test.txt");
        System.out.println("文件名：" + file.getName());
        System.out.println("路径：" + file.getPath());
        System.out.println("绝对路径：" + file.getAbsolutePath());
        System.out.println("文件大小：" + file.length());
        System.out.println("父目录路径名字符串：" + file.getParent());
        System.out.println("最近修改时间：" + file.lastModified());
    }

    /**
     * 遍历操作
     */
    @Test
    public void testList(){
        /**
         * staic File[] listRoots()	        列出所有的根目录（Window中就是所有系统的盘符）
         * list()		                    返回指定目录下的文件或者目录名，(抽象路径的String数组)，包含隐藏文件，对于文件这样操作会返回null。
         * list(FilenameFilter filter)	    返回指定目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
         * listFiles()		                返回指定目录下的文件或者目录对象（绝对路径的File类实例数组），包含隐藏文件，对于文件这样操作会返回null。
         * listFiles(FilenameFilter filter)	返回指定目录中符合过滤条件的子文件或子目录，对于文件这样操作会返回null。
         */
        File file = new File("d:\\io");
        FileTypeFilter fileTypeFilter = new FileTypeFilter(".txt");
        System.out.println("根目录：" + Arrays.toString(File.listRoots()));
        /*for (String f : file.list()){
            System.out.println(f);
        }*/
        System.out.println(Arrays.toString(file.list()));
        System.out.println(Arrays.toString(file.list(fileTypeFilter)));
        /*for (File f : file.listFiles()){
            System.out.println(f.getName());
        }*/
        System.out.println(Arrays.toString(file.listFiles()));
        System.out.println(Arrays.toString(file.listFiles(fileTypeFilter)));
    }

}
