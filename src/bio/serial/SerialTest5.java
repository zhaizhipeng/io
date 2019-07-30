package bio.serial;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerialTest5 {

    private static final String TMP_FILE = "serialtest5.txt";

    public static void main(String[] args) {
        testWrite();
        testRead();
    }

    private static void testWrite() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
            Box box = new Box("desk", 80, 48);
            out.writeObject(box);
            System.out.println("testWrite box: " + box);
            box = new Box("room", 100, 50);
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