package cn.edu.service;

import cn.edu.vo.Major;

import java.util.List;
/**
 * @Author wys
 * @ClassName MajorService
 * @Description //TODO 专业管理接口
 * @Date 20:33 2020/2/15
 * @Param
 * @return
 **/
public interface MajorService {

    List<Major>getMajorList();
    int insert(Major major);
    int update(Major major);
    int delete(String id);
}
