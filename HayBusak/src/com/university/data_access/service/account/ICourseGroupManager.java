package com.university.data_access.service.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.CourseGroup;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 02.08.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public interface ICourseGroupManager {


    public void add(CourseGroup group) throws InternalErrorException, DuplicateDataException;

    public void addSubjects(List<Integer> groupsId,List<Integer> subjectsId) throws InternalErrorException, DuplicateDataException;

    public CourseGroup getById(int id) throws InternalErrorException, EntityNotFoundException;

    public List<CourseGroup> getAll() throws InternalErrorException;

    public List<CourseGroup> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(CourseGroup group) throws InternalErrorException, DuplicateDataException, EntityNotFoundException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;


}
