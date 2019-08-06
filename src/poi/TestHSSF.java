package poi;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * 死磕 HSSF
 */
public class TestHSSF {

    private final String pathName = "E:\\Test.xls";

    /**
     * 创建 WorkBook 和 Sheet
     * @throws IOException
     */
    @Test
    public void testCreateWorkBookAndSheet() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        hssfWorkbook.createSheet("测试");
        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);

        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 创建单元格
     * @throws IOException
     */
    @Test
    public void testCreateCell() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("测试");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(true);
        hssfRow.createCell(1).setCellValue("测试");
        hssfRow.createCell(2).setCellValue(new Date());
        hssfRow.createCell(3).setCellValue(12.345);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);

        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 创建文档摘要信息
     * @throws IOException
     */
    @Test
    public void testCreateSummary() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();

        // 创建文档信息
        hssfWorkbook.createInformationProperties();
        // 摘要信息
        DocumentSummaryInformation documentSummaryInformation = hssfWorkbook.getDocumentSummaryInformation();
        // 类别
        documentSummaryInformation.setCategory("类别:Excel文件");
        // 管理者
        documentSummaryInformation.setManager("管理者:小Z");
        // 公司
        documentSummaryInformation.setCompany("公司:--");

        // 摘要信息
        SummaryInformation summaryInformation = hssfWorkbook.getSummaryInformation();
        // 主题
        summaryInformation.setSubject("主题:--");
        // 标题
        summaryInformation.setTitle("标题:测试文档");
        // 作者
        summaryInformation.setAuthor("作者:小Z");
        // 备注
        summaryInformation.setComments("备注:POI测试文档");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 测试写数据 - 无模板
     */
    @Test
    public void testWriteNoModel() throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        for (int i = 0; i < 63536; i++){
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue("i = " + i + ",j = " + j);
            }
        }

        File file = new File("Test.xlsx");
        if(! file.exists()){
            file.createNewFile();
        }

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

}
