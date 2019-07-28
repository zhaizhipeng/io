package bio.bytestream;

import bio.model.Student;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;

/**
 * 死磕 ObjectOutputStream
 */
public class TestObjectOutputStream {

    @Test
    public void testWrite() throws IOException {

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("test.txt"));

        // 写入引用数据类型
        out.writeBoolean(true);
        out.writeByte((byte)65);
        out.writeChar('a');
        out.writeInt(20131015);
        out.writeFloat(3.14F);
        out.writeDouble(1.414D);

        // 写入HashMap对象
        HashMap map = new HashMap();
        map.put("one", "red");
        map.put("two", "green");
        map.put("three", "blue");
        out.writeObject(map);

        out.close();
    }

    /**
     * 将对象写入文件：序列化
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void writeObj() throws IOException {
        Student stu = new Student("哪吒",3);
        //建立数据通道
        FileOutputStream fileOutputStream = new FileOutputStream(new File("stu.txt"));
        //建立对象输出流对象
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(stu);
    }

}
