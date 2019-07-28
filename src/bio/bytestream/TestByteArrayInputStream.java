package bio.bytestream;

import java.io.ByteArrayInputStream;

/**
 * 死磕 ByteArrayInputStream
 */
public class TestByteArrayInputStream {

    private static final int LEN = 5;

    // 对应26个英文字母“abcdefghijklmnopqrstuvwxyz”
    private static final byte[] ArrayLetters = {
            0x61, 0x62, 0x63, 0x64, 0x65, 0x66, 0x67, 0x68, 0x69, 0x6A, 0x6B, 0x6C, 0x6D, 0x6E, 0x6F,
            0x70, 0x71, 0x72, 0x73, 0x74, 0x75, 0x76, 0x77, 0x78, 0x79, 0x7A
    };

    public static void main(String[] args) {
        String buf = new String(ArrayLetters);
        System.out.println("ArrayLetters = " + buf);
        tesByteArrayInputStream() ;
    }

    /**
     * ByteArrayInputStream的API测试函数
     */
    private static void tesByteArrayInputStream() {

        // 创建ByteArrayInputStream字节流，内容是ArrayLetters数组
        ByteArrayInputStream bais = new ByteArrayInputStream(ArrayLetters);

        // 从字节流中读取5个字节
        for (int i=0; i<LEN; i++) {
            // 若能继续读取下一个字节，则读取下一个字节
            if (bais.available() >= 0) {
                // 读取“字节流的下一个字节”
                int tmp = bais.read();
                System.out.printf("%d : 0x%s\n", i, Integer.toHexString(tmp));
            }
        }

        // 若“该字节流”不支持标记功能，则直接退出
        if (!bais.markSupported()) {
            System.out.println("mark not supported!");
            return ;
        }

        // 标记“字节流中下一个被读取的位置”。即--标记“0x66”，因为因为前面已经读取了5个字节，所以下一个被读取的位置是第6个字节”
        // (01), ByteArrayInputStream 类的 mark(0)函数中的“参数0”是没有实际意义的。
        // (02), mark() 与 reset()是配套的，reset()会将“字节流中下一个被读取的位置”重置为“mark()中所保存的位置”
        bais.mark(0);

        // 跳过5个字节。
        bais.skip(5);

        // 从字节流中读取5个数据
        byte[] buf = new byte[LEN];
        bais.read(buf, 0, LEN);
        String str1 = new String(buf);
        System.out.printf("str1=%s\n", str1);

        // 重置“字节流”：即，将“字节流中下一个被读取的位置”重置到“mark()所标记的位置”，即0x66。
        bais.reset();

        // 从“重置后的字节流”中读取5个字节到buf中。
        bais.read(buf, 0, LEN);
        String str2 = new String(buf);
        System.out.printf("str2=%s\n", str2);
    }

}