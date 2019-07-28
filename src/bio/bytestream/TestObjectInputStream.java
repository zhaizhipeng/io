package bio.bytestream;

import bio.model.Student;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 死磕 ObjectInputStream
 */
public class TestObjectInputStream {

    private static final String TMP_FILE = "student.tmp";

    @Test
    public void testRead() throws IOException, ClassNotFoundException {

        ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));

        System.out.printf("boolean:%b\n" , in.readBoolean());
        System.out.printf("byte:%d\n" , (in.readByte()&0xff));
        System.out.printf("char:%c\n" , in.readChar());
        System.out.printf("int:%d\n" , in.readInt());
        System.out.printf("float:%f\n" , in.readFloat());
        System.out.printf("double:%f\n" , in.readDouble());

        // 读取HashMap对象
        HashMap map = (HashMap) in.readObject();
        Iterator iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry)iter.next();
            System.out.printf("%-6s -- %s\n" , entry.getKey(), entry.getValue());
        }

        in.close();
    }

    /**
     * 从文件中读取对象信息：反序列化
     * @throws IOException
     */
    @Test
    public void readObj() throws IOException, ClassNotFoundException {
        //建立数据通道
        FileInputStream fileInputStream=new FileInputStream(new File("stu.txt"));
        //建立对象输入流对象
        ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
        Student stu = (Student) objectInputStream.readObject();
        System.out.println(stu);
    }

}
