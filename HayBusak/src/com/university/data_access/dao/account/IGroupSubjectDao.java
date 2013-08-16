package com.university.data_access.dao.account;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.GroupSubject;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 02.08.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public interface IGroupSubjectDao {

    public void add(List<GroupSubject> groupSubjects) throws DatabaseException;

    public GroupSubject getById(int id) throws DatabaseException;

    public List<GroupSubject> getByParams(Map<String, Object> params) throws DatabaseException;

    public void update(GroupSubject data) throws DatabaseException, EntityNotFoundException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;


}
