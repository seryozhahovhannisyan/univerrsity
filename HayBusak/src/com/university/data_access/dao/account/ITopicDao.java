package com.university.data_access.dao.account;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.Topic;

import java.util.List;
import java.util.Map;

public interface ITopicDao {

    public void add(Topic data) throws DatabaseException;

    public void addUserTopic(int topicId,List<Integer> userIdes) throws DatabaseException;

    public Topic getById(int id) throws DatabaseException;

    public List<Topic> getByParams(Map<String, Object> params) throws DatabaseException;

    public List<Topic> getUserTopics(int userId) throws DatabaseException;

    public void update(Topic data) throws DatabaseException, EntityNotFoundException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;
}
