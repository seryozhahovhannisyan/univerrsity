package com.university.data_access.dao.quiz;

import com.university.model.quiz.Question;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 13.08.13
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
public interface IQuestionDao {

    public void add(Question question);

    public Question getById(int id);
    public List<Question> getByParams(Map<String, Object> params);

    public void edit(Question question);
    public void delete(int id);
}
