package com.university.data_access.dao.account;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.CourseGroup;

import java.util.List;
import java.util.Map;

public interface ICourseGroupDao {

    public void add(CourseGroup group) throws DatabaseException;

    public void addSubjects(List<Integer> groupsId, List<Integer> subjectsId) throws DatabaseException;

    public CourseGroup getById(int id) throws DatabaseException;

    public List<CourseGroup> getAll() throws DatabaseException;

    public List<CourseGroup> getByParams(Map<String, Object> params) throws DatabaseException;

    public void update(CourseGroup user) throws DatabaseException, EntityNotFoundException;

    public void delete(int id) throws DatabaseException, EntityNotFoundException;
}
