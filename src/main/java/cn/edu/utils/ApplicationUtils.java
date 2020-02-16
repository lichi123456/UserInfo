package cn.edu.utils;

import java.util.UUID;

/**
 * @ClassName ApplicationUtils
 * @Description TODO 设置主键id
 * @Author wys5
 * @Date 2020/2/15 16:23
 * @Version 1.0
 **/
public class ApplicationUtils {
    public static String randomUUID() {
        return UUID.randomUUID().toString().toUpperCase();
    }

    public static String GUID32() {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
