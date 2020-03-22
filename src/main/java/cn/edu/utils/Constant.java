package cn.edu.utils;

/**
 * @ClassName Constant
 * @Description TODO 常数常量定义
 * @Author wys5
 * @Date 2020/2/15 16:18
 * @Version 1.0
 **/
public class Constant {
    // 假删除标识-已删除
    public static final String  isDelete = "Y";
    // 假删除标识-使用中
    public static final String  isNotDelete ="N";
    //管理员标识
    public static final String isAdmin = "0";
    //教师标识
    public static final String isTeacher = "2";
    //学生标识
    public static final String isStudent = "1";
    // 证书数据导出表头
    public static final String[] CERTIFICATE_EXCEL_HEADER =
            {"*学号","*姓名","*证书名称","*指导老师","*指导老师","*指导老师","*获取证书时间","*证书等级"};
    //证书数据类型
    public static final String CERTIFICATE_TYPE = "CERTIFICATE";
    // 工作室数据表头
    public  static final String[] STUDENT_EXCEL_HEADER = {"*学号","*姓名","*性别","*年级","*班级","*班级序号","*电话号码","*QQ","*邮箱"};
    // 工作室学生
    public static final String STUDENT_TYPE ="STUDENT";

}
