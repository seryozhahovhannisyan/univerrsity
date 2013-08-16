package com.university.data_access.service.account.impl;

import com.university.data_access.dao.account.IGroupSubjectDao;
import com.university.data_access.dao.account.ISubjectDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.model.account.GroupSubject;
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
public class GroupSubjectManagerImpl implements IGroupSubjectManager {

    private IGroupSubjectDao groupSubjectDao;

    public void setGroupSubjectDao(IGroupSubjectDao groupSubjectDao) {
        this.groupSubjectDao = groupSubjectDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(List<GroupSubject> groupSubjects) throws InternalErrorException, DuplicateDataException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("groupSubjects", groupSubjects);

            List<GroupSubject> groupSubjectList = groupSubjectDao.getByParams(params);

            if (groupSubjectList != null && groupSubjectList.size() > 0) {
                //throw new DuplicateDataException();
            }

            groupSubjectDao.add(groupSubjects);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public GroupSubject getById(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            return groupSubjectDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<GroupSubject> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return groupSubjectDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void update(GroupSubject groupSubject) throws InternalErrorException, DuplicateDataException, EntityNotFoundException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("groupId", groupSubject.getGroupId());
            params.put("subjectId", groupSubject.getSubjectId());

            List<GroupSubject> groupSubjects = groupSubjectDao.getByParams(params);

            if (groupSubjects != null && groupSubjects.size() > 0) {
                throw new DuplicateDataException();
            }

            groupSubjectDao.update(groupSubject);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            groupSubjectDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
