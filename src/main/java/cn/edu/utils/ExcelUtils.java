package cn.edu.utils;

import cn.edu.dto.CertificateDto;

import cn.edu.vo.Certificate;
import cn.edu.vo.Student;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private final static String excel2007U =".xlsx";
    public  List<List<Object>> importExcel(MultipartFile file) throws Exception{

        List<List<Object>> list;
        //创建Excel工作薄
        Workbook work = new XSSFWorkbook(file.getInputStream());  //2007+
        if(null == work){
            throw new Exception("创建Excel工作薄为空！");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        list = new ArrayList<List<Object>>();
        //遍历Excel中所有的sheet
        for (int i = 0; i <work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if(sheet==null){continue;}

            //遍历当前sheet中的所有行
            for (int j = sheet.getFirstRowNum()+1; j <= sheet.getLastRowNum(); j++) {
                row = sheet.getRow(j);
                if(row==null||row.getFirstCellNum()==j){continue;}

                //遍历所有的列
                List<Object> li = new ArrayList<>();
                for (int y = row.getFirstCellNum(); y <row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    li.add(this.getCellValue(cell));
                }
                list.add(li);
            }
        }
        return list;
    }


    /**
     * @Author lichi
     * @ClassName getCellValue
     * @Description //TODO  对表格中数值进行格式化
     * @Date 10:04 2020/1/19
     * @Param [cell]
     * @return java.lang.Object
     **/
    public  Object getCellValue(Cell cell){

        //用String接收所有返回的值
        String value = null;
        DecimalFormat df = new DecimalFormat("0");  //格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");  //日期格式化
        DecimalFormat df2 = new DecimalFormat("0.00");  //格式化数字

        switch (cell.getCellType()) {
            case STRING:  //String类型的数据
                value =  cell.getStringCellValue();
                break;

            case NUMERIC:   //数值类型(取值用cell.getNumericCellValue() 或cell.getDateCellValue())
                if("General".equals(cell.getCellStyle().getDataFormatString())){
                    value = df.format(cell.getNumericCellValue());
                }else if(HSSFDateUtil.isCellDateFormatted(cell)){
                    value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                }else{
                    value = df2.format(cell.getNumericCellValue());
                }
                break;

            case BOOLEAN:  //Boolean类型
                value = String.valueOf(cell.getBooleanCellValue());
                break;


            case  FORMULA: //表达式类型
                value = String.valueOf(cell.getCellFormula());
                break;

            case  ERROR: //异常类型 不知道何时算异常
                value=String.valueOf(cell.getErrorCellValue());
                break;

            case  BLANK:  //空，不知道何时算空
                value = "";
                break;

            default:
                value = "";
                break;
        }
        if(value.equals("")||value==null){
            value = "";
        }
        if (cell == null) {
            return "";
        }
        return value;
    }

    /**
     * 导出数据
     * @param objects
     * @param excelHeader
     * @param exportType 导入数据的类型
     * @param excelSheet 导入数据的表名
     * @return
     * @throws IOException
     */

    public ResponseEntity<byte[]> exportExcel(List<CertificateDto> objects, String [] excelHeader, String exportType, String excelSheet) throws IOException {

        HttpHeaders headers = null;
        ByteArrayOutputStream baos = null;
        try{
            //获取表头长度
            int length = excelHeader.length;
            int[] ColumnLength = new int[length];//列宽数组
            for(int i = 0;i < length;i++){
                ColumnLength[i]=2;
            }
            Workbook wb = new XSSFWorkbook();

            Sheet sheet = wb.createSheet("证书信息");//创建工作表
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
            if(exportType.equals(CERTIFICATE_TYPE)){
                CertificateDto certificateDto = null;
                //导入数据库数据
                for(int i = 0; i < objects.size();i++){
                    row = sheet.createRow(i+1);
                    certificateDto= (CertificateDto) objects.get(i);
                    row.createCell(0).setCellValue(certificateDto.getStudent().getStudentCode());
                    row.createCell(1).setCellValue(certificateDto.getStudent().getStudentName());
                    row.createCell(2).setCellValue(certificateDto.getCertificate().getCertificateName());
                    if(certificateDto.getTeacher1().getTeacherName()!=null && "".compareTo(certificateDto.getTeacher1().getTeacherName())!=0){
                        row.createCell(3).setCellValue(certificateDto.getTeacher1().getTeacherName());
                    }
                    if(certificateDto.getTeacher2().getTeacherName()!=null && "".compareTo(certificateDto.getTeacher2().getTeacherName())!=0){
                        row.createCell(4).setCellValue(certificateDto.getTeacher2().getTeacherName());
                    }
                    if(certificateDto.getTeacher3().getTeacherName()!=null && "".compareTo(certificateDto.getTeacher3().getTeacherName())!=0){
                        row.createCell(5).setCellValue(certificateDto.getTeacher3().getTeacherName());
                    }
                    if(certificateDto.getCertificate().getCertificateDate()!=null && "".compareTo(String.valueOf(certificateDto.getCertificate().getCertificateDate()))!=0){
                        row.createCell(6).setCellValue(certificateDto.getCertificate().getCertificateDate());
                    }
                    if(certificateDto.getCertificate().getCertificateLevel()!=null && "".compareTo(String.valueOf(certificateDto.getCertificate().getCertificateLevel()))!=0){
                        row.createCell(7).setCellValue(certificateDto.getCertificate().getCertificateLevel());
                    }

                }
            }


            headers = new HttpHeaders();
            headers.setContentDispositionFormData("attachment",
                    new String((excelSheet+excel2007U).getBytes("UTF-8"), "iso-8859-1"));
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            baos = new ByteArrayOutputStream();
            wb.write(baos);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.CREATED);
    }



}
