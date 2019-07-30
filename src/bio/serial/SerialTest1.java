package bio.serial;

import java.io.*;

public class SerialTest1 {

    private static final String TMP_FILE = "serialtest1.txt";

    public static void main(String[] args) {
        // 将“对象”通过序列化保存
        testWrite();
        // 将序列化的“对象”读出来
        testRead();
    }

    private static void testWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
            Box box = new Box("desk", 80, 48);
            out.writeObject(box);
            System.out.println("testWrite box: " + box);
            out.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void testRead() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));
            Box box = (Box) in.readObject();
            System.out.println("testRead  box: " + box);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/**
 * Box类“支持序列化”。因为Box实现了Serializable接口。
 *
 * 实际上，一个类只需要实现Serializable即可实现序列化，而不需要实现任何函数。
 */
class Box implements Serializable {
    private static int width;
    private transient int height;
    private String name;

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();//使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
        out.writeInt(height);
        out.writeInt(width);
        //System.out.println("Box--writeObject width="+width+", height="+height);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();//defaultReadObject()补充自动序列化
        height = in.readInt();
        width = in.readInt();
        //System.out.println("Box---readObject width="+width+", height="+height);
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}

/*class Box implements Serializable {
    private static int width;
    private transient int height;
    private String name;
    private transient Thread thread = new Thread() {
        @Override
        public void run() {
            System.out.println("Serializable thread");
        }
    };

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    private void writeObject(ObjectOutputStream out) throws IOException{
        out.defaultWriteObject();//使定制的writeObject()方法可以利用自动序列化中内置的逻辑。
        out.writeInt(height);
        out.writeInt(width);
        //System.out.println("Box--writeObject width="+width+", height="+height);
    }

    private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException{
        in.defaultReadObject();//defaultReadObject()补充自动序列化
        height = in.readInt();
        width = in.readInt();
        //System.out.println("Box---readObject width="+width+", height="+height);
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}*/

/*class Box implements Externalizable {
    private int width;
    private int height;
    private String name;

    public Box() {
    }

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}*/

/*class Box implements Externalizable {
    private int width;
    private int height;
    private String name;

    public Box() {
    }

    public Box(String name, int width, int height) {
        this.name = name;
        this.width = width;
        this.height = height;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
        out.writeInt(width);
        out.writeInt(height);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
        width = in.readInt();
        height = in.readInt();
    }

    @Override
    public String toString() {
        return "["+name+": ("+width+", "+height+") ]";
    }
}*/
