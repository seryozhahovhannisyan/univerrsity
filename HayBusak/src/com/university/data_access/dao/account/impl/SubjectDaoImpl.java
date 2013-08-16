package com.university.data_access.dao.account.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.account.ISubjectDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.Subject;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:21
 * To change this template use File | Settings | File Templates.
 */
public class SubjectDaoImpl implements ISubjectDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(Subject subject) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsSubject.add", subject);
            subject.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Subject getById(int id) throws DatabaseException {
        try {
            return (Subject)sqlMapClient.queryForObject("nsSubject.getById",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Subject> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsSubject.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(Subject subject) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsSubject.update",subject);
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
            int count = sqlMapClient.delete("nsSubject.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
