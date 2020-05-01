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
    public static final String  IS_DELETE = "Y";
    // 假删除标识-使用中
    public static final String  IS_NOT_DELETE ="N";
    //管理员标识
    public static final String IS_ADMIN = "0";
    //教师标识
    public static final String IS_TEACHER = "2";
    //学生标识
    public static final String IS_STUDENT = "1";
    // 图片名称长度
    public static final Integer IMAGE_URL_LENGTH = 32;
    public static final String IMAGE_WEB_PATH = "account/img";

    // 证书数据导出表头
    public static final String[] CERTIFICATE_EXCEL_HEADER =
            {"学号1","姓名1","学号2","姓名2","学号3","姓名3","证书名称","类别","级别","证书等级","获取证书时间","指导老师1","指导老师2","指导老师3"};
    //证书数据类型
    public static final String CERTIFICATE_TYPE = "CERTIFICATE";
    // 工作室数据表头
    public  static final String[] STUDENT_EXCEL_HEADER = {"学号","姓名","性别","院系","专业","班级","所属工作室分组","电话号码","QQ","邮箱"};
    // 工作室学生
    public static final String STUDENT_TYPE ="STUDENT";
    //性别
    public static final String[] SEX_TYPE = {"男","女"};
    //教师
    public static final String[] TEACHER_EXCEL_HEADER = {"工号","姓名","性别","电话号码","QQ号","电子邮箱"};

    /**
     * Excel错误类型
     */
    //当前列为空
    public static final String ROW_IS_EMPTY  = "内容为空";
    //当前列格式不正确
    public static final String ROW_VALIDATE_ERROR = "格式错误";
    //当前列长度错误
    public static final String ROW_LENGTH_ERROR = "长度不正确";
    //当前列内容不存在
    public static final String IS_NOT_EXIST = "不存在";
    //当前内容已存在
    public static final String IS_EXIST = "已存在";

}
