package cn.edu.utils;
/**
 * @ClassName: Picture
 * @Author: lichi
 * @Date: 2020/4/30 2:14
 * @Description:
 * @Version: 1.0
 */

import java.io.File;

/**
 *
 *
 *@description:
 *@author: Andy
 *@time: 2020/4/30 2:14
 *
 */
public class Picture {
    public static int deletePicture(String imageUrl,String path){
        int flag = 0;
        path = path+imageUrl;
        System.out.println(path+"地址");
        File file = new File(path);
        if(file.exists()){
            file.delete();
            flag = 1;
        }
        return flag;
    }
}
