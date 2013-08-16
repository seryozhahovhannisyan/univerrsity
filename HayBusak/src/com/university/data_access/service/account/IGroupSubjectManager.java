package com.university.data_access.service.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
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
public interface IGroupSubjectManager {

    public void add(List<GroupSubject> groupSubjects) throws InternalErrorException, DuplicateDataException;

    public GroupSubject getById(int id) throws InternalErrorException, EntityNotFoundException;

    public List<GroupSubject> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(GroupSubject data) throws InternalErrorException, DuplicateDataException, EntityNotFoundException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;


}
