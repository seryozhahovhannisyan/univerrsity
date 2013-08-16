package com.university.data_access.dao.account;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.Subject;

import java.util.List;
import java.util.Map;

public interface ISubjectDao {

    public void add(Subject subject) throws DatabaseException;

    public Subject getById(int id) throws DatabaseException;

    public List<Subject> getByParams(Map<String, Object> params) throws DatabaseException;

    public void update(Subject subject) throws DatabaseException, EntityNotFoundException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;
}
