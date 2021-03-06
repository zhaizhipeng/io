package poi;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 死磕 SXSSF
 */
public class SXSSFTest {

    /**
     * 测试写数据 - 无模板
     */
    @Test
    public void testWriteNoModel() throws IOException {

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 1000000; i++){
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue("i = " + i + ",j = " + j);
            }
        }

        File file = new File("e:\\Test.xlsx");
        if(! file.exists()){
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

}
