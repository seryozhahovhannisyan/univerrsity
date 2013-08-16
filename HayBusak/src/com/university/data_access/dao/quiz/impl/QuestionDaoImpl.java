package com.university.data_access.dao.quiz.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.quiz.IQuestionDao;
import com.university.model.quiz.Question;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 1:08
 * To change this template use File | Settings | File Templates.
 */
public class QuestionDaoImpl implements IQuestionDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

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

    @Override
    public void edit(Question question) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void delete(int id) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
