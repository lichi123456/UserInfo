package cn.edu.service;

import cn.edu.vo.Matchs;

import java.util.List;

/**
 * @Author wys
 * @ClassName MatchService
 * @Description //TODO 赛事管理接口
 * @Date 20:33 2020/2/15
 * @Param
 * @return
 **/
public interface MatchService {
    /**
     * 得到所有的赛事信息
     * @return
     */
    List<Matchs> getAll();

}
