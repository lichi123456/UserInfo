package cn.edu.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * @ClassName: FileService
 * @Author: lichi
 * @Date: 2020/4/6 21:51
 * @Description:
 * @Version: 1.0
 */
public interface FileService {
     Map<String, String> uploadReal(String fileName, MultipartFile file);

}
