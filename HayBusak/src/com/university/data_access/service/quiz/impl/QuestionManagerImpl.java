package com.university.data_access.service.quiz.impl;

import com.university.data_access.dao.quiz.IQuestionDao;
import com.university.data_access.service.quiz.IQuestionManager;
import com.university.model.quiz.Question;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 1:10
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class QuestionManagerImpl implements IQuestionManager {

    private IQuestionDao questionDao;

    public void setQuestionDao(IQuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Question question) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Question getById(int id) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Question> getByParams(Map<String, Object> params) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void edit(Question question) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
