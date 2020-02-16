package cn.edu.service.impl;

import cn.edu.dao.MajorMapper;
import cn.edu.entity.Major;
import cn.edu.service.MajorService;
import cn.edu.utils.ApplicationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName MajorServiceImpl
 * @Description TODO 专业管理实现类
 * @Author wys5
 * @Date 2020/2/15 16:52
 * @Version 1.0
 **/
@Service
public class MajorServiceImpl implements MajorService {
    @Autowired
    private MajorMapper majorMapper;

    /**
     * @Author wys
     * @ClassName getMajorList
     * @Description //TODO  获取专业列表
     * @Date 16:59 2020/2/15
     * @Param []
     * @return java.util.List<cn.edu.entity.Major>
     **/
    @Override
    public List<Major> getMajorList() {
        return majorMapper.selectAll();
    }

    /**
     * @Author wys
     * @ClassName insert
     * @Description //TODO  新增
     * @Date 17:00 2020/2/15
     * @Param [major, facultyId]
     * @return int
     **/
    @Override
    public int insert(Major major) {
        major.setMajorId(ApplicationUtils.GUID32());
//        major.setCreateUser();
        return majorMapper.insert(major);
    }

    /**
     * @Author wys
     * @ClassName update
     * @Description //TODO 更新
     * @Date 17:00 2020/2/15
     * @Param [major]
     * @return int
     **/
    @Override
    public int update(Major major) {
//        major.setUpdateUser();
        major.setUpdateTime(new Date());
        return majorMapper.updateByPrimaryKeySelective(major);
    }

    /**
     * @Author wys
     * @ClassName delete
     * @Description //TODO 真删除
     * @Date 17:00 2020/2/15
     * @Param [id]
     * @return int
     **/
    @Override
    public int delete(String id) {
        return majorMapper.deleteByPrimaryKey(id);
    }
}