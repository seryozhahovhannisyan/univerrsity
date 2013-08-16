package com.university.data_access.service.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.Topic;

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
public interface ITopicManager {


    public void add(Topic data,List<Integer> userIdes, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException;

    public Topic getById(int id) throws InternalErrorException, EntityNotFoundException;

    public List<Topic> getByParams(Map<String, Object> params) throws InternalErrorException;

    public List<Topic> getUserTopics(int userId) throws InternalErrorException;

    public void update(Topic data) throws InternalErrorException, DuplicateDataException, EntityNotFoundException;

    public void delete(int id,String path) throws InternalErrorException, EntityNotFoundException;


}
