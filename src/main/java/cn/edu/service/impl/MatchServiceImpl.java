package cn.edu.service.impl;

import cn.edu.dao.MatchsMapper;
import cn.edu.service.MatchService;
import cn.edu.vo.Matchs;
import org.apache.poi.ss.formula.functions.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MatchServiceImpl
 * @Description TODO 赛事管理实现类
 * @Author wys5
 * @Date 2020/2/15 20:32
 * @Version 1.0
 **/
@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchsMapper matchsMapper;
    @Override
    public List<Matchs> getAll() {
        List<Matchs> matches = matchsMapper.selectAll();
        return matches;
    }
}
