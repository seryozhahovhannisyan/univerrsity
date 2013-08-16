package com.university.data_access.service.account.impl;

import com.university.data_access.dao.account.IUserDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.IUserManager;
import com.university.data_access.util.DataStorage;
import com.university.model.account.Topic;
import com.university.model.account.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 02.08.13
 * Time: 6:39
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class UserManagerImpl implements IUserManager {

    private IUserDao userDao;
    private DataStorage dataStorage;

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(User user, File photo, String photoFileName, String photoContentType) throws InternalErrorException, DuplicateDataException {
        try {
            boolean isEmailExists = userDao.isEmailExists(user.getEmail());

            if (isEmailExists) {
                throw new DuplicateDataException("User email already exists [" + user.getName() + "]");
            }

            /*if (photoFileName != null && photoFileName.trim().length() != 0) {
                String absPath = dataStorage.add(photo, Profile.USER.getName(), user.getEmail(), photoFileName);
                user.setPhotoPath(absPath);
                user.setHasPhoto(true);
            } else {
                user.setPhotoPath(getDefaultPhotoPath());
                user.setHasPhoto(false);
            }*/

            userDao.add(user);

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public User login(String email, String password, int langId) throws InternalErrorException, EntityNotFoundException {
        try {
            User user = userDao.login(email, password, langId);

            if (user == null) {
                throw new EntityNotFoundException("Please registered");
            }
            return user;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public User getUserById(int id, int langId) throws InternalErrorException, EntityNotFoundException {
        try {
            User user = userDao.getById(id, langId);
            if (user == null) {
                throw new EntityNotFoundException("The user not found");
            }
            return user;
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<User> getAll(int langId) throws InternalErrorException {
        try {
            return userDao.getAll(langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<User> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return userDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW,
            rollbackFor = {EntityNotFoundException.class, DuplicateDataException.class, InternalErrorException.class})
    @Override
    public void update(User user) throws InternalErrorException, DuplicateDataException, EntityNotFoundException {
        try {
            boolean isEmailExists = userDao.isEmailExists(user.getEmail());
            if (isEmailExists) {
                throw new DuplicateDataException("User email already exists ["
                        + user.getName() + "]");
            }


            userDao.update(user);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id) throws InternalErrorException, EntityNotFoundException {

        try {

            userDao.deleteById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException(e);
        }
    }
}
