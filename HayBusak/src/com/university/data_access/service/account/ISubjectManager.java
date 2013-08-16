package com.university.data_access.service.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.Subject;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 02.08.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public interface ISubjectManager {


    public void add(Subject subject) throws InternalErrorException, DuplicateDataException;

    public Subject getById(int id) throws InternalErrorException, EntityNotFoundException;

    public List<Subject> getByParams(Map<String, Object> params) throws InternalErrorException;

    public void update(Subject subject) throws InternalErrorException, DuplicateDataException, EntityNotFoundException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;


}
