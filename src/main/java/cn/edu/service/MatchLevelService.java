package cn.edu.service;

import cn.edu.vo.MatchLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MatchLevelService
 * @Description TODO 赛事级别管理类（国家级、省级、校级等）
 * @Author wys5
 * @Date 2020/2/15 20:35
 * @Version 1.0
 **/
public interface MatchLevelService {
    List<MatchLevel> getmatchLevel();
}
