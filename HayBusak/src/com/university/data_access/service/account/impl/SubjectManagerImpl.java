package com.university.data_access.service.account.impl;

import com.university.data_access.dao.account.ISubjectDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ISubjectManager;
import com.university.model.account.Subject;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:07
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class SubjectManagerImpl implements ISubjectManager {

    private ISubjectDao subjectDao;

    public void setSubjectDao(ISubjectDao subjectDao) {
        this.subjectDao = subjectDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Subject subject) throws InternalErrorException, DuplicateDataException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", subject.getName());
            List<Subject> subjects = subjectDao.getByParams(params);

            if (subjects != null && subjects.size() > 0) {
                throw new DuplicateDataException();
            }

            subjectDao.add(subject);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Subject getById(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            return subjectDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Subject> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return subjectDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void update(Subject subject) throws InternalErrorException, DuplicateDataException, EntityNotFoundException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", subject.getName());
            List<Subject> subjects = subjectDao.getByParams(params);

            if (subjects != null && subjects.size() > 0) {
                throw new DuplicateDataException();
            }

            subjectDao.update(subject);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            subjectDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
