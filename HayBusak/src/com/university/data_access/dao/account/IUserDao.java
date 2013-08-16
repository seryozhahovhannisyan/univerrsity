package com.university.data_access.dao.account;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.User;

import java.util.List;
import java.util.Map;

public interface IUserDao {


    public void add(User user) throws DatabaseException;

    public User login(String email, String password,int langId) throws DatabaseException;

    public User getById(int id,int langId) throws DatabaseException;

    public List<User> getAll(int langId) throws DatabaseException;

    public boolean isEmailExists(String email) throws DatabaseException;

    public List<User> getByParams(Map<String,Object> params) throws DatabaseException;

    public void update(User user) throws DatabaseException, EntityNotFoundException;

    public void deleteById(int id) throws DatabaseException, EntityNotFoundException;
}
