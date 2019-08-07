package poi;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;

import java.io.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
     * 创建批注
     * @throws IOException
     */
    @Test
    public void testCreateComments() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Test");
        HSSFPatriarch patr = sheet.createDrawingPatriarch();
        // 创建批注位置
        HSSFClientAnchor anchor = patr.createAnchor(10, 10, 10, 10, 3, 1, 6,3);
        // 创建批注
        HSSFComment comment = patr.createCellComment(anchor);
        // 设置批注内容
        comment.setString(new HSSFRichTextString("这是一个批注"));
        // 设置批注作者
        comment.setAuthor("小Z");
        // 设置批注默认显示
        comment.setVisible(true);
        HSSFCell hssfCell = sheet.createRow(2).createCell(1);
        hssfCell.setCellValue("测试");
        // 把批注赋值给单元格
        hssfCell.setCellComment(comment);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 创建页眉、页脚
     * @throws IOException
     */
    @Test
    public void testCreateHeaderAndFooter() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Test");

        // 得到页眉
        HSSFHeader header =sheet.getHeader();
        header.setLeft("页眉左边");
        header.setRight("页眉右边");
        header.setCenter("页眉中间");
        // 得到页脚
        HSSFFooter footer = sheet.getFooter();
        footer.setLeft("页脚左边");
        footer.setRight("页脚右边");
        footer.setCenter(HSSFFooter.page());

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     *设置单元格样式
     * @throws IOException
     */
    @Test
    public void testSetCellStyle() throws IOException {

        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Test");

        // 创建行
        HSSFRow row = sheet.createRow(0);

        // 创建样式
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();

        // 设置日期格式--使用Excel内嵌的格式
        HSSFCell hssfCell = row.createCell(0);
        hssfCell.setCellValue(new Date());
        hssfCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        hssfCell.setCellStyle(hssfCellStyle);

        // 设置保留2位小数--使用Excel内嵌的格式
        hssfCell = row.createCell(1);
        hssfCell.setCellValue(12.3456789);
        hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        hssfCell.setCellStyle(hssfCellStyle);

        // 设置货币格式--使用自定义的格式
        hssfCell = row.createCell(2);
        hssfCell.setCellValue(12345.6789);
        hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(hssfWorkbook.createDataFormat().getFormat("￥#,##0"));
        hssfCell.setCellStyle(hssfCellStyle);

        // 设置百分比格式--使用自定义的格式
        hssfCell = row.createCell(3);
        hssfCell.setCellValue(0.123456789);
        hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(hssfWorkbook.createDataFormat().getFormat("0.00%"));
        hssfCell.setCellStyle(hssfCellStyle);

        // 设置中文大写格式--使用自定义的格式
        hssfCell = row.createCell(4);
        hssfCell.setCellValue(12345);
        hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(hssfWorkbook.createDataFormat().getFormat("[DbNum2][$-804]0"));
        hssfCell.setCellStyle(hssfCellStyle);

        // 设置科学计数法格式--使用自定义的格式
        hssfCell = row.createCell(5);
        hssfCell.setCellValue(12345);
        hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(hssfWorkbook.createDataFormat().getFormat("0.00E+00"));
        hssfCell.setCellStyle(hssfCellStyle);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 合并单元格
     * @throws IOException
     */
    @Test
    public void testSetAddMergedRegion() throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = sheet.createRow(0);
        //合并列
        HSSFCell cell = hssfRow.createCell(0);
        cell.setCellValue("合并列");
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, 5);
        sheet.addMergedRegion(region);

        //合并行
        cell = hssfRow.createCell(6);
        cell.setCellValue("合并行");
        region = new CellRangeAddress(0, 5, 6, 6);
        sheet.addMergedRegion(region);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 单元格对齐方式
     * @throws IOException
     */
    @Test
    public void testSetAlignment() throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellValue("单元格对齐");

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        // 水平居中
        hssfCellStyle.setAlignment(HorizontalAlignment.CENTER);
        // 垂直居中
        hssfCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 自动换行
        hssfCellStyle.setWrapText(true);
        // 缩进
        hssfCellStyle.setIndention((short)5);
        // 文本旋转，这里的取值是从-90到90，而不是0-180度
        hssfCellStyle.setRotation((short)60);
        hssfCell.setCellStyle(hssfCellStyle);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 设置边框
     * @throws IOException
     */
    @Test
    public void testSetBorder() throws IOException {
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue("设置边框");

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        // 上边框
        hssfCellStyle.setBorderTop(BorderStyle.HAIR);
        // 下边框
        hssfCellStyle.setBorderBottom(BorderStyle.DASHED);
        // 左边框
        hssfCellStyle.setBorderLeft(BorderStyle.DASH_DOT);
        // 右边框
        hssfCellStyle.setBorderRight(BorderStyle.MEDIUM_DASH_DOT_DOT);

        // 上边框颜色
        hssfCellStyle.setTopBorderColor(IndexedColors.RED.getIndex());
        // 下边框颜色
        hssfCellStyle.setBottomBorderColor(IndexedColors.YELLOW.getIndex());
        // 左边框颜色
        hssfCellStyle.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        // 右边框颜色
        hssfCellStyle.setRightBorderColor(IndexedColors.GREEN.getIndex());
        hssfCell.setCellStyle(hssfCellStyle);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 设置字体
     * @throws IOException
     */
    @Test
    public void testSetFont() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue("设置字体");

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        HSSFFont hssfFont = hssfWorkbook.createFont();
        // 设置字体名称
        hssfFont.setFontName("华文行楷");
        // 设置字号
        hssfFont.setFontHeightInPoints((short)28);
        // 设置字体颜色
        hssfFont.setColor(IndexedColors.RED.getIndex());
        // 设置下划线
        hssfFont.setUnderline(FontFormatting.U_DOUBLE);
        // 设置上标下标
        hssfFont.setTypeOffset(FontFormatting.U_DOUBLE);
        // 设置删除线
        hssfFont.setStrikeout(true);
        hssfCellStyle.setFont(hssfFont);
        hssfCell.setCellStyle(hssfCellStyle);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 设置背景和纹理
     * @throws IOException
     */
    @Test
    public void testSetBackGround() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue("设置背景和纹理");

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        // 设置图案颜色
        hssfCellStyle.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
        // 设置图案背景色
        hssfCellStyle.setFillBackgroundColor(IndexedColors.RED.getIndex());
        // 设置图案样式
        hssfCellStyle.setFillPattern(FillPatternType.FINE_DOTS);
        hssfCell.setCellStyle(hssfCellStyle);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 设置宽度和高度
     * @throws IOException
     */
    @Test
    public void testSetWidthAndHeight() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(1);
        HSSFCell hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue("123456789012345678901234567890");
        // 设置第一列的宽度是31个字符宽度
        hssfSheet.setColumnWidth(1, 31 * 256);
        // 设置行的高度是50个点
        hssfRow.setHeightInPoints(50);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 日期类型判断
     * @throws IOException
     */
    @Test
    public void testDateType() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(1);
        HSSFCell hssfCell = hssfRow.createCell(1);
        // 设置日期数据
        hssfCell.setCellValue(new Date());
        System.out.println(DateUtil.isCellDateFormatted(hssfCell));
        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));
        hssfCell.setCellStyle(hssfCellStyle);
        System.out.println(DateUtil.isCellDateFormatted(hssfCell));

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 基本计算
     * @throws IOException
     */
    @Test
    public void testFormula() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        HSSFCell hssfCell = hssfRow.createCell(0);
        hssfCell.setCellFormula("2+3*4");

        hssfCell = hssfRow.createCell(1);
        hssfCell.setCellValue(10);

        hssfCell = hssfRow.createCell(2);
        hssfCell.setCellFormula("A1*B1");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * SUM函数
     * @throws IOException
     */
    @Test
    public void testFormula2() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(1);
        hssfRow.createCell(1).setCellValue(2);
        hssfRow.createCell(2).setCellValue(3);
        hssfRow.createCell(3).setCellValue(4);
        hssfRow.createCell(4).setCellValue(5);

        hssfRow = hssfSheet.createRow(1);
        // 等价于"A1+C1"
        hssfRow.createCell(0).setCellFormula("SUM(A1,C1)");
        // 等价于"B1+C1+D1"
        hssfRow.createCell(1).setCellFormula("SUM(B1:D1)");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 日期函数
     * @throws IOException
     */
    @Test
    public void testFormula3() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFCellStyle hssfCellStyle = hssfWorkbook.createCellStyle();
        hssfCellStyle.setDataFormat(hssfWorkbook.createDataFormat().getFormat("yyyy-mm-dd"));

        HSSFRow hssfRow = hssfSheet.createRow(0);
        Calendar date = Calendar.getInstance();
        HSSFCell hssfCell = hssfRow.createCell(0);
        date.set(2011,2, 7);
        hssfCell.setCellValue(date.getTime());

        // 第一个单元格开始时间设置完成
        hssfCell.setCellStyle(hssfCellStyle);
        hssfCell = hssfRow.createCell(1);
        date.set(2014,4, 25);
        hssfCell.setCellValue(date.getTime());
        // 第一个单元格结束时间设置完成
        hssfCell.setCellStyle(hssfCellStyle);
        hssfCell = hssfRow.createCell(3);
        hssfCell.setCellFormula("CONCATENATE(DATEDIF(A1,B1,\"y\"),\"年\")");
        hssfCell = hssfRow.createCell(4);
        hssfCell.setCellFormula("CONCATENATE(DATEDIF(A1,B1,\"m\"),\"月\")");
        hssfCell = hssfRow.createCell(5);
        hssfCell.setCellFormula("CONCATENATE(DATEDIF(A1,B1,\"d\"),\"日\")");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 字符串相关函数
     * @throws IOException
     */
    @Test
    public void testFormula4() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue("abcdefg");
        hssfRow.createCell(1).setCellValue("aa bb cc dd ee fF GG");
        hssfRow.createCell(3).setCellFormula("UPPER(A1)");
        hssfRow.createCell(4).setCellFormula("PROPER(B1)");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * IF函数
     * @throws IOException
     */
    @Test
    public void testFormula5() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(12);
        hssfRow.createCell(1).setCellValue(23);
        hssfRow.createCell(3).setCellFormula("IF(A1>B1,\"A1大于B1\",\"A1小于等于B1\")");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * CountIf 和 SumIf函数
     * @throws IOException
     */
    @Test
    public void testFormula6() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(57);
        hssfRow.createCell(1).setCellValue(89);
        hssfRow.createCell(2).setCellValue(56);
        hssfRow.createCell(3).setCellValue(67);
        hssfRow.createCell(4).setCellValue(60);
        hssfRow.createCell(5).setCellValue(73);
        hssfRow.createCell(7).setCellFormula("COUNTIF(A1:F1,\">=60\")");
        hssfRow.createCell(8).setCellFormula("SUMIF(A1:F1,\">=60\",A1:F1)");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * Lookup函数
     * @throws IOException
     */
    @Test
    public void testFormula7() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(0);
        hssfRow.createCell(1).setCellValue(59);
        hssfRow.createCell(2).setCellValue("不及格");

        hssfRow = hssfSheet.createRow(1);
        hssfRow.createCell(0).setCellValue(60);
        hssfRow.createCell(1).setCellValue(69);
        hssfRow.createCell(2).setCellValue("及格");

        hssfRow = hssfSheet.createRow(2);
        hssfRow.createCell(0).setCellValue(70);
        hssfRow.createCell(1).setCellValue(79);
        hssfRow.createCell(2).setCellValue("良好");

        hssfRow = hssfSheet.createRow(3);
        hssfRow.createCell(0).setCellValue(80);
        hssfRow.createCell(1).setCellValue(100);
        hssfRow.createCell(2).setCellValue("优秀");

        hssfRow = hssfSheet.createRow(4);
        hssfRow.createCell(0).setCellValue(75);
        hssfRow.createCell(1).setCellFormula("LOOKUP(A5,$A$1:$A$4,$C$1:$C$4)");
        hssfRow.createCell(2).setCellFormula("VLOOKUP(A5,$A$1:$C$4,3,true)");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 随机函数
     * @throws IOException
     */
    @Test
    public void testFormula8() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow row = hssfSheet.createRow(0);
        // 取0-1之间的随机数
        row.createCell(0).setCellFormula("RAND()");
        // 取0-100之间的随机整数
        row.createCell(1).setCellFormula("int(RAND()*100)");
        // 取10-20之间的随机实数
        row.createCell(2).setCellFormula("RAND()*10+10");
        // 随机小写字母
        row.createCell(3).setCellFormula("CHAR(INT(RAND()*26)+97)");
        // 随机大写字母
        row.createCell(4).setCellFormula("CHAR(INT(RAND()*26)+65)");
        // 随机大小写字母
        row.createCell(5).setCellFormula("CHAR(INT(RAND()*26)+if(INT(RAND()*2)=0,97,65))");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 获得公式的返回值
     * @throws IOException
     */
    @Test
    public void testFormula9() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(0);
        hssfRow.createCell(0).setCellValue(7);
        hssfRow.createCell(1).setCellValue(8);
        HSSFCell hssfCell = hssfRow.createCell(2);
        hssfCell.setCellFormula("A1*B1+14");
        HSSFFormulaEvaluator hssfFormulaEvaluator = new HSSFFormulaEvaluator(hssfWorkbook);

        // 若Excel文件不是POI创建的，则不必调用此方法
        hssfCell = hssfFormulaEvaluator.evaluateInCell(hssfCell);
        System.out.println("公式计算结果：" + hssfCell.getNumericCellValue());

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 画线
     * @throws IOException
     */
    @Test
    public void testGraph() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFPatriarch patriarch = hssfSheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 0, 0,(short)1, 0,(short)4, 4);
        HSSFSimpleShape shape = patriarch.createSimpleShape(anchor);
        // 设置图形类型
        shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
        // 设置图形样式
        shape.setLineStyle(HSSFShape.LINESTYLE_SOLID);
        // 在POI中线的宽度12700表示1pt,所以这里是0.5pt粗的线条。
        shape.setLineWidth(6350);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 画矩形
     * @throws IOException
     */
    @Test
    public void testGraph2() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFPatriarch patriarch = hssfSheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(255,122,255, 122, (short)1, 0,(short)4, 3);
        HSSFSimpleShape shape = patriarch.createSimpleShape(anchor);
        shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_RECTANGLE);
        // 设置边框样式
        shape.setLineStyle(HSSFShape.LINESTYLE_DASHGEL);
        // 设置填充色
        shape.setFillColor(255, 0, 0);
        // 设置边框宽度
        shape.setLineWidth(25400);
        // 设置边框颜色
        shape.setLineStyleColor(0, 0, 255);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 画圆形
     * @throws IOException
     */
    @Test
    public void testGraph3() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFPatriarch patriarch = hssfSheet.createDrawingPatriarch();
        HSSFClientAnchor anchor = new HSSFClientAnchor(255,122,255, 122, (short)1, 0,(short)4, 3);
        HSSFSimpleShape shape = patriarch.createSimpleShape(anchor);
        shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_OVAL);
        // 设置边框样式
        shape.setLineStyle(HSSFShape.LINESTYLE_DASHGEL);
        // 设置填充色
        shape.setFillColor(255, 0, 0);
        // 设置边框宽度
        shape.setLineWidth(25400);
        // 设置边框颜色
        shape.setLineStyleColor(0, 0, 255);

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 画Grid
     * @throws IOException
     */
    @Test
    public void testGraph4() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        HSSFRow hssfRow = hssfSheet.createRow(2);
        hssfRow.createCell(1);
        hssfRow.setHeightInPoints(240);
        hssfSheet.setColumnWidth(2, 9000);

        int linesCount = 20;
        HSSFPatriarch hssfPatriarch = hssfSheet.createDrawingPatriarch();

        // 由于HSSFClientAnchor中dx只能在0-1023之间,dy只能在0-255之间，这里采用比例的方式
        double xRatio = 1023.0 / (linesCount * 10);
        double yRatio = 255.0 / (linesCount * 10);

        // 画竖线
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 200;
        for (int i = 0; i < linesCount; i++) {
            HSSFClientAnchor hssfClientAnchor = new HSSFClientAnchor();
            hssfClientAnchor.setAnchor((short) 2, 2, (int) (x1 * xRatio),
            (int) (y1 * yRatio), (short) 2, 2, (int) (x2 * xRatio),
            (int) (y2 * yRatio));

            HSSFSimpleShape shape = hssfPatriarch.createSimpleShape(hssfClientAnchor);
            shape.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            x1 += 10;
            x2 += 10;
        }

        // 画横线
        x1 = 0;
        y1 = 0;
        x2 = 200;
        y2 = 0;
        for (int i = 0; i < linesCount; i++) {
            HSSFClientAnchor hssfClientAnchor = new HSSFClientAnchor();
            hssfClientAnchor.setAnchor((short) 2, 2, (int) (x1 * xRatio),
            (int) (y1 * yRatio), (short) 2, 2, (int) (x2 * xRatio),
            (int) (y2 * yRatio));

            HSSFSimpleShape shape2 = hssfPatriarch.createSimpleShape(hssfClientAnchor);
            shape2.setShapeType(HSSFSimpleShape.OBJECT_TYPE_LINE);
            y1 += 10;
            y2 += 10;
        }

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 插入图片
     * @throws IOException
     */
    @Test
    public void testGraph5() throws IOException{
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet hssfSheet = hssfWorkbook.createSheet("Test");

        FileInputStream fileInputStream = new FileInputStream("d:\\POI\\Apache.gif");
        byte[] bytes = new byte[(int)fileInputStream.getChannel().size()];
        fileInputStream.read(bytes);
        int pictureIdx = hssfWorkbook.addPicture(bytes,HSSFWorkbook.PICTURE_TYPE_JPEG);

        HSSFPatriarch hssfPatriarch = hssfSheet.createDrawingPatriarch();
        HSSFClientAnchor hssfClientAnchor = new HSSFClientAnchor(0, 0, 0, 0,(short)0, 0, (short)5, 5);
        HSSFPicture hssfPicture = hssfPatriarch.createPicture(hssfClientAnchor,pictureIdx);
        // 自动调节图片大小,图片位置信息可能丢失
        hssfPicture.resize();

        FileOutputStream fileOutputStream = new FileOutputStream(new File(pathName));
        hssfWorkbook.write(fileOutputStream);
        fileOutputStream.close();
        hssfWorkbook.close();
    }

    /**
     * 从Excel中提取图片
     * @throws IOException
     */
    /*@Test
    public void testGraph6() throws IOException{

        InputStream inputStream = new FileInputStream(filePath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(inputStream);

        List<HSSFPictureData> pictures = hssfWorkbook.getAllPictures();
        for(int i=0;i<pictures.size();i++) {
            HSSFPictureData pic=pictures.get(i);
            String ext = pic.suggestFileExtension();
            if (ext.equals("png")) {
                FileOutputStream fileOutputStream = new FileOutputStream("d:\\POI\\Apache.png");
                fileOutputStream.write(pic.getData());
                fileOutputStream.close();//保存图片
            }
        }

    }*/

    /**
     * 读取Excel内容
     * @throws IOException
     */
    @Test
    public void testRead() throws IOException {

        String filePath = "Test.xls";
        FileInputStream stream = new FileInputStream(filePath);
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook(stream);
        HSSFSheet hssfSheet = hssfWorkbook.getSheet("Test");
        for (Row row : hssfSheet) {
            for (Cell cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 写入Excel内容
     */
    @Test
    public void testWrite() throws IOException {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Test");
        for (int i = 0; i < 100; i++){
            Row row = sheet.createRow(i);
            for (int j = 0; j < 10; j++){
                Cell cell = row.createCell(j);
                cell.setCellValue("i = " + i + ",j = " + j);
            }
        }

        File file = new File("Test.xls");
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

}