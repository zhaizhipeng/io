package bio.bytestream;

import org.junit.Test;

import java.io.*;

/**
 * 死磕 BufferedInputStream
 */
public class TestBufferedInputStream {

    private static final int LEN = 5;

    @Test
    public void testBufferedInputStream() throws IOException {

        // 创建BufferedInputStream字节流，内容是ArrayLetters数组
        File file = new File("bufferedinputstream.txt");
        FilterInputStream in = new BufferedInputStream(new FileInputStream(file), 512);

        // 从字节流中读取5个字节。
        for (int i = 0; i < LEN; i++) {
            if (in.available() >= 0) {
                int tmp = in.read();
                System.out.printf("%d : 0x%s\n", i, Integer.toHexString(tmp));
            }
        }

        // 若“该字节流”不支持标记功能，则直接退出
        if (!in.markSupported()) {
            System.out.println("make not supported!");
            return ;
        }

        // 标记“当前索引位置”，即标记第6个位置的元素--“f”
        // 1024对应marklimit
        in.mark(1024);

        // 跳过22个字节。
        in.skip(22);

        // 读取5个字节
        byte[] buf = new byte[LEN];
        in.read(buf, 0, LEN);

        // 将buf转换为String字符串。
        String str1 = new String(buf);
        System.out.printf("str1=%s\n", str1);

        // 重置“输入流的索引”为mark()所标记的位置，即重置到“f”处。
        in.reset();
        // 从“重置后的字节流”中读取5个字节到buf中。即读取“fghij”
        in.read(buf, 0, LEN);
        String str2 = new String(buf);
        System.out.printf("str2=%s\n", str2);

        in.close();
    }

}
