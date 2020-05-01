package cn.edu.utils;

import ch.qos.logback.core.util.ContextUtil;
import cn.edu.dto.CertificateDto;

import cn.edu.dto.FacultyAndMajorAndClass;
import cn.edu.dto.StudentDto;
import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import cn.edu.vo.Teacher;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.edu.utils.Constant.CERTIFICATE_TYPE;
import static cn.edu.utils.Constant.STUDENT_TYPE;

/**
 * @ClassName ExcelUtils
 * @Description TODO
 * @Author lichi
 * @Date 2020/3/5 13:29
 * @Version 1.0
 **/
public class ExcelUtils {
    private final static String excel2007U = ".xlsx";

    public List<List<Object>> importExcel(MultipartFile file) throws Exception {

        List<List<Object>> list;
        //创建Excel工作薄
        Workbook work = new XSSFWorkbook(file.getInputStream());  //2007+
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        sheet = work.getSheetAt(0);
        //遍历当前sheet中的所有行
        for (int j = sheet.getFirstRowNum() + 1; j <= sheet.getLastRowNum(); j++) {
            row = sheet.getRow(j);
            if (row == null || row.getFirstCellNum() == j) {
                continue;
            }

            //遍历所有的列
            List<Object> li = new ArrayList<>();
            for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                cell = row.getCell(y);
                li.add(this.getCellValue(cell));
            }
            list.add(li);
        }

        return list;
    }


    /**
     * @return java.lang.Object
     * @Author lichi
     * @ClassName getCellValue
     * @Description //TODO  对表格中数值进行格式化
     * @Date 10:04 2020/1/19
     * @Param [cell]
     **/
    public Object getCellValue(Cell cell) {

        //用String接收所有返回的值
        String value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case STRING:  //String类型的数据
                value = cell.getStringCellValue();
                break;

            case NUMERIC:   //数值类型(取值用cell.getNumericCellValue() 或cell.getDateCellValue())
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;

            case BOOLEAN:  //Boolean类型
                value = String.valueOf(cell.getBooleanCellValue());
                break;


            case FORMULA: //表达式类型
                value = String.valueOf(cell.getCellFormula());
                break;

            case ERROR: //异常类型 不知道何时算异常
                value = String.valueOf(cell.getErrorCellValue());
                break;

            case BLANK:  //空，不知道何时算空
                value = "";
                break;

            default:
                value = "";
                break;
        }
        if (value.equals("") || value == null) {
            value = "";
        }
        if (cell == null) {
            return "";
        }
        return value;
    }

//    /**
//     * 导出数据
//     *
//     * @param objects
//     * @param excelHeader
//     * @param exportType  导入数据的类型
//     * @param excelSheet  导入数据的表名
//     * @return
//     * @throws IOException
//     */
//
//    public ResponseEntity<byte[]> exportExcel(List<CertificateDto> objects, String[] excelHeader, String exportType, String excelSheet) throws IOException {
//
//        HttpHeaders headers = null;
//        ByteArrayOutputStream baos = null;
//        try {
//            //获取表头长度
//            int length = excelHeader.length;
//            int[] ColumnLength = new int[length];//列宽数组
//            for (int i = 0; i < length; i++) {
//                ColumnLength[i] = 2;
//            }
//            Workbook wb = new XSSFWorkbook();
//
//            Sheet sheet = wb.createSheet("证书信息");//创建工作表
//            Row row = sheet.createRow(0);//创建行
//            CellStyle style = wb.createCellStyle();
//            style.setAlignment(HorizontalAlignment.CENTER);//对齐方式
//            style.setFillForegroundColor((short)43);
//            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
//            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//
//            //导入头部
//            for (int i = 0; i < excelHeader.length; i++) {
//                Cell cell = row.createCell(i);
//                cell.setCellValue(excelHeader[i]);
//                cell.setCellStyle(style);
//            }
//            for (int i = 0; i < excelHeader.length; i++) {
//                sheet.setColumnWidth(i, (short) ColumnLength[i] * 3000);//设置列宽
//            }
//            if (exportType.equals(CERTIFICATE_TYPE)) {
//                CertificateDto certificateDto = null;
//                //导入数据库数据
//                for (int i = 0; i < objects.size(); i++) {
//                    row = sheet.createRow(i + 1);
//                    certificateDto = (CertificateDto) objects.get(i);
//                    row.createCell(0).setCellValue(certificateDto.getStudentCode());
//                    row.createCell(1).setCellValue(certificateDto.getStudentName());
////                    row.createCell(2).setCellValue(certificateDto.getStudentCode());
////                    row.createCell(3).setCellValue(certificateDto.getStudentName());
////                    row.createCell(4).setCellValue(certificateDto.getStudentCode());
////                    row.createCell(5).setCellValue(certificateDto.getStudentName());
//                    row.createCell(6).setCellValue(certificateDto.getCertificateName());
//                    row.createCell(7).setCellValue(certificateDto.getMatchType());
//                    row.createCell(8).setCellValue(certificateDto.getMatchLevel());
//                        row.createCell(9).setCellValue(certificateDto.getCertificateLevel());
//                        row.createCell(10).setCellValue(certificateDto.getCertificateDate());
//                        row.createCell(11).setCellValue(certificateDto.getTeacherName());
////                        row.createCell(11).setCellValue(certificateDto.getTeacher1().getTeacherName());
////                        row.createCell(11).setCellValue(certificateDto.getTeacher1().getTeacherName());
//                }
//            }
//
//
//            headers = new HttpHeaders();
//            headers.setContentDispositionFormData("attachment",
//                    new String((excelSheet + excel2007U).getBytes("UTF-8"), "iso-8859-1"));
//            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            baos = new ByteArrayOutputStream();
//            wb.write(baos);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
//    }


    public ResponseEntity<byte[]> exportExcelStudentModel(String[] faculty, String[] fathrtNameArr, Map<String, String[]> schoolMap,String[] group) {
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try {
            Workbook wb = new XSSFWorkbook();
            String[] excelHeader = Constant.STUDENT_EXCEL_HEADER;
            int[] ColumnLength = new int[excelHeader.length];
            for (int i = 0; i < excelHeader.length; i++) {
                ColumnLength[i] = 2;
            }

            //创建学生EXCEL
            XSSFSheet sheetPro = (XSSFSheet) wb.createSheet("学生列表");
            Row row = sheetPro.createRow(0);//创建行
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);//对齐方式

            style.setFillForegroundColor((short)43);
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            //导入头部
            for (int i = 0; i < excelHeader.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
                sheetPro.setColumnWidth(i,(short)ColumnLength[i]*3000);//设置列宽
            }

            //创建一个专门用来存放地区信息的隐藏sheet页
            //因此也不能在现实页之前创建，否则无法隐藏。
            Sheet hideSheet = wb.createSheet("school");
            int rowId = 0;
            // 设置第一行，存院系的信息
            Row facultyRow = hideSheet.createRow(rowId++);
            facultyRow.createCell(0).setCellValue("院系");
            for (int i = 0; i < faculty.length; i++) {
                Cell facultyCell = facultyRow.createCell(i + 1);
                facultyCell.setCellValue(faculty[i]);
            }
            // 将具体的数据写入到每一行中，行开头为父级区域，后面是子区域。
            for (int i = 0; i < fathrtNameArr.length; i++) {
                String key = fathrtNameArr[i];
                String[] son = schoolMap.get(key);
                Row hiderow = hideSheet.createRow(rowId++);
                hiderow.createCell(0).setCellValue(key);
                for (int j = 0; j < son.length; j++) {
                    Cell cell = hiderow.createCell(j + 1);
                    cell.setCellValue(son[j]);
                }
                // 添加名称管理器
                String range = getRange(1, rowId, son.length);
                Name name = wb.createName();
                //key不可重复,将父区域名作为key
                name.setNameName(key);
                String formula = "school!" + range;
                name.setRefersToFormula(formula);
            }
            //
            //利用名称管理器关联到隐藏sheet中信息
            XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper((XSSFSheet)sheetPro);
            DataValidationConstraint facultyConstraint = dvHelper.createExplicitListConstraint(faculty);
            //添加性别
            String [] sex = Constant.SEX_TYPE;
            XSSFDataValidationHelper helper = new XSSFDataValidationHelper(sheetPro);
            DataValidationConstraint sexConstraint = helper.createExplicitListConstraint(sex);
            CellRangeAddressList sexRangeAddressList = new CellRangeAddressList(1, 102, 2, 2);
            //验证
            DataValidation sexDataValidation = helper.createValidation(sexConstraint, sexRangeAddressList);
            sexDataValidation.createErrorBox("error","请选择正确的性别");
            sexDataValidation.setShowErrorBox(true);
            sexDataValidation.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(sexDataValidation);
            // 四个参数分别是：起始行、终止行、起始列、终止列
            CellRangeAddressList facultyRangeAddressList = new CellRangeAddressList(1, 102, 3, 3);
            DataValidation facultyDataValidation = dvHelper.createValidation(facultyConstraint, facultyRangeAddressList);
            //验证
            facultyDataValidation.createErrorBox("error","请选择正确的院校");
            facultyDataValidation.setShowErrorBox(true);
            facultyDataValidation.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(facultyDataValidation);

            //添加团队
            XSSFDataValidationHelper grouphelper = new XSSFDataValidationHelper(sheetPro);
            DataValidationConstraint groupConstraint = grouphelper.createExplicitListConstraint(group);
            CellRangeAddressList groupRangeAddressList = new CellRangeAddressList(1, 102, 6, 6);
            DataValidation groupDataValidation = grouphelper.createValidation(groupConstraint, groupRangeAddressList);
            //验证
            groupDataValidation.createErrorBox("error","请选择正确的团队");
            groupDataValidation.setShowErrorBox(true);
            groupDataValidation.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(groupDataValidation);


            //设置前100行有效
            for(int i = 2;i < 101;i++){
                setDataValidation("D" ,sheetPro,i,5);
                setDataValidation("E" ,sheetPro,i,6);
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String(("学生信息表模板" + excel2007U).getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            //隐藏下拉栏sheet
            wb.setSheetHidden(1,true);
            wb.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     *  计算formula
     * @param offset 偏移量，如果给0，表示从A列开始，1，就是从B列
     * @param rowId 第几行
     * @param colCount 一共多少列
     * @return 如果给入参 1,1,10. 表示从B1-K1。最终返回 $B$1:$K$1
     * 来源于百度 https://blog.csdn.net/m0_37956938/article/details/78084503
     *
     */
    public String getRange(int offset, int rowId, int colCount) {
        char start = (char)('A' + offset);
        if (colCount <= 25) {
            char end = (char)(start + colCount - 1);
            return "$" + start + "$" + rowId + ":$" + end + "$" + rowId;
        } else {
            char endPrefix = 'A';
            char endSuffix = 'A';
            if ((colCount - 25) / 26 == 0 || colCount == 51) {// 26-51之间，包括边界（仅两次字母表计算）
                if ((colCount - 25) % 26 == 0) {// 边界值
                    endSuffix = (char)('A' + 25);
                } else {
                    endSuffix = (char)('A' + (colCount - 25) % 26 - 1);
                }
            } else {// 51以上
                if ((colCount - 25) % 26 == 0) {
                    endSuffix = (char)('A' + 25);
                    endPrefix = (char)(endPrefix + (colCount - 25) / 26 - 1);
                } else {
                    endSuffix = (char)('A' + (colCount - 25) % 26 - 1);
                    endPrefix = (char)(endPrefix + (colCount - 25) / 26);
                }
            }
            return "$" + start + "$" + rowId + ":$" + endPrefix + endSuffix + "$" + rowId;
        }


    }
    /**
     * 设置有效性
     * @param offset 主影响单元格所在列，即此单元格由哪个单元格影响联动
     * @param sheet
     * @param rowNum 行数
     * @param colNum 列数
     *               原文链接：https://blog.csdn.net/m0_37956938/article/details/78084503
     */
    public static void setDataValidation(String offset,XSSFSheet sheet, int rowNum,int colNum) {
        XSSFDataValidationHelper dvHelper = new XSSFDataValidationHelper(sheet);
        DataValidation data_validation_list;
        data_validation_list = getDataValidationByFormula(
                "INDIRECT($" + offset + (rowNum) + ")", rowNum, colNum,dvHelper);
        sheet.addValidationData(data_validation_list);
    }

    /**
     * 加载下拉列表内容
     * @param formulaString
     * @param naturalRowIndex
     * @param naturalColumnIndex
     * @param dvHelper
     * @return
     */
    private static  DataValidation getDataValidationByFormula(
            String formulaString, int naturalRowIndex, int naturalColumnIndex,XSSFDataValidationHelper dvHelper) {
        // 加载下拉列表内容
        // 举例：若formulaString = "INDIRECT($A$2)" 表示规则数据会从名称管理器中获取key与单元格 A2 值相同的数据，
        //如果A2是江苏省，那么此处就是江苏省下的市信息。
        XSSFDataValidationConstraint dvConstraint = (XSSFDataValidationConstraint) dvHelper.createFormulaListConstraint(formulaString);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        int firstRow = naturalRowIndex -1;
        int lastRow = naturalRowIndex - 1;
        int firstCol = naturalColumnIndex - 1;
        int lastCol = naturalColumnIndex - 1;
        CellRangeAddressList regions = new CellRangeAddressList(firstRow,
                lastRow, firstCol, lastCol);
        // 数据有效性对象
        // 绑定
        XSSFDataValidation data_validation_list = (XSSFDataValidation) dvHelper.createValidation(dvConstraint, regions);
        data_validation_list.setEmptyCellAllowed(false);
        if (data_validation_list instanceof XSSFDataValidation) {
            data_validation_list.setSuppressDropDownArrow(true);
            data_validation_list.setShowErrorBox(true);
        } else {
            data_validation_list.setSuppressDropDownArrow(false);
        }
        // 设置输入信息提示信息
        data_validation_list.createPromptBox("下拉选择提示", "请使用下拉方式选择合适的值！");
        // 设置输入错误提示信息
        //data_validation_list.createErrorBox("选择错误提示", "你输入的值未在备选列表中，请下拉选择合适的值！");
        return data_validation_list;
    }
    public ResponseEntity<byte[]> exportExcelTeacherModel(){
        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            Workbook wb = new XSSFWorkbook();
            String[] excelHeader = Constant.TEACHER_EXCEL_HEADER;
            int[] ColumnLength = new int[excelHeader.length];
            for (int i = 0; i < excelHeader.length; i++) {
                ColumnLength[i] = 2;
            }
            //创建教师EXCEL
            XSSFSheet sheetPro = (XSSFSheet) wb.createSheet("教师表格");
            Row row = sheetPro.createRow(0);//创建行
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);//对齐方式
            style.setFillForegroundColor((short)43);
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //导入头部
            for (int i = 0; i < excelHeader.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
            }
            for(int i = 0; i < excelHeader.length; i++){
                sheetPro.setColumnWidth(i,(short)ColumnLength[i]*3000);//设置列宽
            }
            String [] sex = Constant.SEX_TYPE;
            XSSFDataValidationHelper helper = new XSSFDataValidationHelper(sheetPro);
            DataValidationConstraint sexConstraint = helper.createExplicitListConstraint(sex);
            CellRangeAddressList sexRangeAddressList = new CellRangeAddressList(1, 102, 2, 2);
            DataValidation sexDataValidation = helper.createValidation(sexConstraint, sexRangeAddressList);
            //验证
            sexDataValidation.createErrorBox("error","请选择正确的性别");
            sexDataValidation.setShowErrorBox(true);
            sexDataValidation.setSuppressDropDownArrow(true);
            sheetPro.addValidationData(sexDataValidation);
            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String(("教师信息表模板" + excel2007U).getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            wb.write(baos);

        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    };


    /**
     * @Author wys
     * @ClassName exportExcel
     * @Description //TODO  导出方法
     * @Date 10:03 2020/1/19
     * @Param [students]
     * @return org.apache.poi.ss.usermodel.Workbook
     **/
    public ResponseEntity<byte[]> exportExcelStudent(List<StudentDto> studentDtos) throws IOException {

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //0，1，2，3，4，5，6，7，8
            String[] excelHeader = Constant.STUDENT_EXCEL_HEADER;
            int[] ColumnLength = new int[excelHeader.length];
            for (int i = 0; i < excelHeader.length; i++) {
                ColumnLength[i] = 2;
            }//列宽数组
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("学生信息");//创建工作表
            Row row = sheet.createRow(0);//创建行
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);//对齐方式
            style.setFillForegroundColor((short)43);
            style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            //导入头部
            for(int i = 0; i < excelHeader.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
            }
            for(int i = 0; i < excelHeader.length; i++){
                sheet.setColumnWidth(i,(short)ColumnLength[i]*3000);//设置列宽
            }
            StudentDto studentDto = null;
            //导入数据库数据
            for(int i = 0; i < studentDtos.size();i++){
                row = sheet.createRow(i+1);
                studentDto=studentDtos.get(i);
                if(studentDto.getStudentCode()!=null && "".compareTo(studentDto.getStudentCode())!=0){
                    row.createCell(0).setCellValue(studentDto.getStudentCode());
                }
                if(studentDto.getStudentName()!=null && "".compareTo(studentDto.getStudentName())!=0){
                    row.createCell(1).setCellValue(studentDto.getStudentName());
                }
                if(studentDto.getStudentSex()!=null && "".compareTo(studentDto.getStudentSex())!=0){
                    row.createCell(2).setCellValue(studentDto.getStudentSex());
                }
                if(studentDto.getFacultyName()!=null && "".compareTo(studentDto.getFacultyName())!=0){
                    row.createCell(3).setCellValue(studentDto.getFacultyName());
                }
                if(studentDto.getMajorName()!=null && "".compareTo(studentDto.getMajorName())!=0){
                    row.createCell(4).setCellValue(studentDto.getMajorName());
                }
                if(studentDto.getClassName()!=null && "".compareTo(studentDto.getClassName())!=0){
                    row.createCell(5).setCellValue(studentDto.getClassName());
                }
                if(studentDto.getGroupName()!=null && "".compareTo(studentDto.getGroupName())!=0){
                    row.createCell(6).setCellValue(studentDto.getGroupName());
                }
                if(studentDto.getStudentTel()!=null && studentDto.getStudentTel().compareTo("")!=0){
                    row.createCell(7).setCellValue(studentDto.getStudentTel());
                }
                if(studentDto.getStudentQq()!=null &&studentDto.getStudentQq().compareTo("")!=0){
                    row.createCell(8).setCellValue(studentDto.getStudentQq());
                }
                if(studentDto.getStudentEmail()!=null &&studentDto.getStudentEmail().compareTo("")!=0){
                    row.createCell(9).setCellValue(studentDto.getStudentEmail());
                }
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String(("学生信息表"+excel2007U).getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            wb.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    public ResponseEntity<byte[]> exportExcelTeacher(List<Teacher> teachers) throws IOException {

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //0，1，2，3，4，5，6，7，8.....
            String[] excelHeader = Constant.TEACHER_EXCEL_HEADER;
            int[] ColumnLength = new int[excelHeader.length];
            for (int i = 0; i < excelHeader.length; i++) {
                ColumnLength[i] = 2;
            }//列宽数组
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("学生信息");//创建工作表
            Row row = sheet.createRow(0);//创建行
            CellStyle style = wb.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);//对齐方式

            //导入头部
            for(int i = 0; i < excelHeader.length;i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(excelHeader[i]);
                cell.setCellStyle(style);
            }
            for(int i = 0; i < excelHeader.length; i++){
                sheet.setColumnWidth(i,(short)ColumnLength[i]*3000);//设置列宽
            }
            Teacher teacher = null;
            //导入数据库数据
            for(int i = 0; i < teachers.size();i++){
                row = sheet.createRow(i+1);
                teacher=teachers.get(i);
                if(teacher.getTeacherCode()!=null && "".compareTo(teacher.getTeacherCode())!=0){
                    row.createCell(0).setCellValue(teacher.getTeacherCode());
                }
                if(teacher.getTeacherName()!=null && "".compareTo(teacher.getTeacherName())!=0){
                    row.createCell(1).setCellValue(teacher.getTeacherName());
                }
                if(teacher.getTeacherSex()!=null && "".compareTo(teacher.getTeacherSex())!=0){
                    row.createCell(2).setCellValue(teacher.getTeacherSex());
                }
                if(teacher.getTeacherTel()!=null && "".compareTo(teacher.getTeacherTel())!=0){
                    row.createCell(3).setCellValue(teacher.getTeacherTel());
                }
                if(teacher.getTeacherQq()!=null && "".compareTo(teacher.getTeacherQq())!=0){
                    row.createCell(4).setCellValue(teacher.getTeacherQq());
                }
                if(teacher.getTeacherEmail()!=null && "".compareTo(teacher.getTeacherEmail())!=0){
                    row.createCell(5).setCellValue(teacher.getTeacherEmail());
                }
            }

            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String(("教师信息表"+excel2007U).getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            wb.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }

    /**
     * @Author wys
     * @ClassName setErrorMessage
     * @Description //TODO  错误信息提示
     * @Date 13:09 2020/4/30
     * @Param [index, name, problemText]
     * @return cn.edu.utils.Result
     **/
    public static Result setErrorMessage(int index, String name,String problemText){
        Result result = new Result();
        result.setMessage("第["+index+"]行, ["+name+problemText+"] ,请校正后再重新导入！");
        result.setSuccess(false);
        return result;
    }


}
