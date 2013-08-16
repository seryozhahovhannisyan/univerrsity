package com.university.data_access.dao.account.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.account.ISubjectDao;
import com.university.data_access.dao.account.ITopicDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.Topic;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:21
 * To change this template use File | Settings | File Templates.
 */
public class TopicDaoImpl implements ITopicDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(Topic data) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsTopic.add", data);
            data.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addUserTopic(int topicId, List<Integer> userIdes) throws DatabaseException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("topicId",topicId) ;
        params.put("userIdes",userIdes) ;
        try {
             sqlMapClient.insert("nsTopic.addUserTopic", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Topic getById(int id) throws DatabaseException {
        try {
            return (Topic)sqlMapClient.queryForObject("nsTopic.getById",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Topic> getUserTopics(int userId) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsTopic.getUserTopics",userId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Topic> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsTopic.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Topic data) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsTopic.update",data);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.delete("nsTopic.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
