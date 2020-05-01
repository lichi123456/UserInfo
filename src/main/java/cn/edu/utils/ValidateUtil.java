package cn.edu.utils;


import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * @ClassName ValidateUtil
 * @Description TODO
 * @Author wys5
 * @Date 2020/4/29 13:23
 * @Version 1.0
 **/
/**
 * 用户校验数据格式
 *
 */
public class ValidateUtil {

    /**
     * @Author wys
     * @ClassName isMobileNo
     * @Description //TODO  验证是否为手机号
     * @Date 13:25 2020/4/29
     * @Param [mobileNo]
     * @return boolean
     **/
    public static boolean isMobileNo(String mobileNo) {
        // 1、(13[0-9])|(15[02789])|(18[679])|(17[0-9]) 13段 或者15段 18段17段的匹配
        // 2、\\d{8} 整数出现8次
        boolean flag = false;
        Pattern p = Pattern.compile("^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$");
        Matcher match = p.matcher(mobileNo);
        if (mobileNo != null && !"".equals(mobileNo.trim())) {
            flag = match.matches();
        }
        return flag;
    }

    /**
     * @Author wys
     * @ClassName isEmail
     * @Description //TODO 验证是否为正确的邮箱号
     * @Date 13:25 2020/4/29
     * @Param [email]
     * @return boolean
     **/
    public static boolean isEmail(String email) {
        // 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
        // 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
        // {1,3}表示可以出现一次或两次或者三次.
        String reg = "\\w+@(\\w+\\.){1,3}\\w+";
        Pattern pattern = Pattern.compile(reg);
        boolean flag = false;
        if (email != null && !"".equals(email)) {
            Matcher matcher = pattern.matcher(email);
            flag = matcher.matches();
        }
        return flag;
    }


    /**
     * @Author wys
     * @ClassName isNumeric
     * @Description //TODO  利用正则表达式判断字符串是否是数字
     * @Date 13:25 2020/4/29
     * @Param [str]
     * @return boolean
     **/
    public static boolean isNumeric(String str){
        boolean flag = false;
        Pattern pattern = Pattern.compile("[0-9]*");
        if (str != null && !"".equals(str)) {
            Matcher isNum = pattern.matcher(str);
            flag = isNum.matches();
        }
        return flag;
    }
    /**
     * @Author wys
     * @ClassName isStudentCode
     * @Description //TODO 学号为11位
     * @Date 16:47 2020/4/29
     * @Param [str]
     * @return boolean
     **/
    public static boolean isStudentCode(String str){
        boolean flag = false;
        if (str.length() == 11) {
            flag = true;
        }
        return flag;
    }

    /**
     * @Author wys
     * @ClassName isTeacherCode
     * @Description //TODO  工号为6位
     * @Date 14:49 2020/5/1
     * @Param [str]
     * @return boolean
     **/
    public static boolean isTeacherCode(String str){
        boolean flag = false;
        if (str.length() == 6) {
            flag = true;
        }
        return flag;
    }
}
