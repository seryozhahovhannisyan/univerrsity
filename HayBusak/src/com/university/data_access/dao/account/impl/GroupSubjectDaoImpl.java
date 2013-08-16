package com.university.data_access.dao.account.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.account.IGroupSubjectDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.GroupSubject;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 08.08.13
 * Time: 1:54
 * To change this template use File | Settings | File Templates.
 */
public class GroupSubjectDaoImpl implements IGroupSubjectDao{

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(List<GroupSubject> groupSubjects) throws DatabaseException {
        try {
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("groupSubjects",groupSubjects);
            sqlMapClient.insert("nsGroupSubject.add",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public GroupSubject getById(int id) throws DatabaseException {
        try {
            return (GroupSubject)sqlMapClient.queryForObject("nsGroupSubject.getById",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<GroupSubject> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsGroupSubject.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(GroupSubject data) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsGroupSubject.update",data);
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
            int count = sqlMapClient.delete("nsGroupSubject.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
