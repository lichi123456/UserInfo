package cn.edu.service.impl;

import cn.edu.dao.MatchLevelMapper;
import cn.edu.dao.MatchsMapper;
import cn.edu.service.MatchLevelService;
import cn.edu.service.MatchService;
import cn.edu.vo.MatchLevel;
import cn.edu.vo.Matchs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MatchLevelServiceImpl
 * @Description TODO 赛事级别管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:35
 * @Version 1.0
 **/
@Service
public class MatchLevelServiceImpl implements MatchLevelService {
    @Autowired
    private MatchLevelMapper matchLevelMapper;
    @Override
    public List<MatchLevel> getmatchLevel() {
        List<MatchLevel> matchLevels = matchLevelMapper.selectAll();

        return matchLevels;
    }
}
