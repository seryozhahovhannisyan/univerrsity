package com.university.data_access.service.account.impl;

import com.university.data_access.dao.account.ICourseGroupDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.model.account.CourseGroup;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 19:59
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class CourseGroupManagerImpl implements ICourseGroupManager {

    private ICourseGroupDao courseGroupDao;

    public void setCourseGroupDao(ICourseGroupDao courseGroupDao) {
        this.courseGroupDao = courseGroupDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(CourseGroup group) throws InternalErrorException, DuplicateDataException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", group.getName());
            List<CourseGroup> courseGroups = courseGroupDao.getByParams(params);

            if (courseGroups != null && courseGroups.size() > 0) {
                throw new DuplicateDataException();
            }

            courseGroupDao.add(group);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void addSubjects(List<Integer> groupsId, List<Integer> subjectsId) throws InternalErrorException, DuplicateDataException {
        try {
            courseGroupDao.addSubjects(groupsId, subjectsId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public CourseGroup getById(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            return courseGroupDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CourseGroup> getAll() throws InternalErrorException {
        try {
            return courseGroupDao.getAll();
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<CourseGroup> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return courseGroupDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void update(CourseGroup group) throws InternalErrorException, DuplicateDataException, EntityNotFoundException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", group.getName());
            List<CourseGroup> courseGroups = courseGroupDao.getByParams(params);

            if (courseGroups != null && courseGroups.size() > 0) {
                throw new DuplicateDataException();
            }

            courseGroupDao.update(group);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            courseGroupDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
