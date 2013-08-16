package com.university.data_access.dao.account.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.account.IUserDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UserDaoImpl implements IUserDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(User user) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsUser.add", user);
            user.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User login(String email, String password,int langId) throws DatabaseException {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("email", email);
        params.put("password", password);
        params.put("langId", langId);

        try {
            return (User) sqlMapClient.queryForObject("nsUser.login", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public User getById(int id,int langId) throws DatabaseException {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        params.put("langId", langId);
        try {
            return (User) sqlMapClient.queryForObject("nsUser.getById", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<User> getAll(int langId) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsUser.getAll",langId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public boolean isEmailExists(String email) throws DatabaseException {
        try {
            Integer count =(Integer)sqlMapClient.queryForObject("nsUser.isEmailExists",email);
            return count != 0;
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<User> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsUser.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(User user) throws DatabaseException, EntityNotFoundException {
        try {

            int count = sqlMapClient.update("nsUser.update", user);
            if (count != 1) {
                throw new EntityNotFoundException("The user not exists " + user);
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void deleteById(int id) throws DatabaseException, EntityNotFoundException {
        try {

            int count = sqlMapClient.delete("nsUser.update", id);
            if (count != 1) {
                throw new EntityNotFoundException("The user not exists likes id " + id);
            }


        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}