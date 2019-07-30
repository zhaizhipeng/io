package bio.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SerialTest2 {

    private static final String TMP_FILE = "serialabletest2.txt";

    public static void main(String[] args) {
        testWrite();
        testRead();
    }

    private static void testWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void testRead() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}