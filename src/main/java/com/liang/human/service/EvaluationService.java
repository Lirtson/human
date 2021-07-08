package com.liang.human.service;

import com.liang.human.dao.EvaluationDao;
import com.liang.human.model.EvaluationVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EvaluationService {
    @Resource
    EvaluationDao evaluationDao;
    public List<EvaluationVO> getComments() {
        return evaluationDao.listCommentsVo();
    }

    public void addComment(String content, Long uid) {
        evaluationDao.addOne(content,uid);
    }
}
