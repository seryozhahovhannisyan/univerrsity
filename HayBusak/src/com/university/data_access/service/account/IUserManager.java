package com.university.data_access.service.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.User;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 02.08.13
 * Time: 6:36
 * To change this template use File | Settings | File Templates.
 */
public interface IUserManager {


    public void add(User user,File photo,String photoFileName,String photoContentType) throws InternalErrorException, DuplicateDataException;

    public User login(String email, String password,int langId) throws InternalErrorException, EntityNotFoundException;

    public User getUserById(int id,int langId) throws InternalErrorException, EntityNotFoundException;

    public List<User> getAll(int langId) throws InternalErrorException;

    public List<User> getByParams(Map<String,Object> params) throws InternalErrorException;

    public void update(User user) throws InternalErrorException, DuplicateDataException, EntityNotFoundException;

    public void delete(int id) throws InternalErrorException, EntityNotFoundException;

    /**
     * #################################################################################################################
     * Accounts
     * #################################################################################################################
     */

}
