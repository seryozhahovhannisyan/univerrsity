package com.university.data_access.service.account.impl;

import com.university.data_access.dao.account.ITopicDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ITopicManager;
import com.university.data_access.util.DataStorage;
import com.university.model.account.Subject;
import com.university.model.account.Topic;
import com.university.web.util.ConstantGeneral;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 07.08.13
 * Time: 1:47
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class TopicManagerImpl implements ITopicManager {

    private ITopicDao topicDao;
    private DataStorage dataStorage;

    public void setTopicDao(ITopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Topic data, List<Integer> userIdes, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException {
        try {
            //check if the topics is exists
            /*Map<String, Object> params = new HashMap<String, Object>();
            params.put("title", data.getTitle());
            List<Topic> topics = topicDao.getByParams(params);


            if (topics != null && topics.size() > 0) {
                throw new DuplicateDataException();
            }*/
            //todo do store the topics
            //dataStorage.setPath(path);
            //String pcg = dataStorage.createPackages(path);

            String fn = fileName;

            /*fn = dataStorage.setFileName(fileName, "new filename");

            String dataPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR +
                    dataStorage.add(file, pcg, fn);
*/
            //add topic to db topic table
            topicDao.add(data);
            //add topic to db topic table
            topicDao.addUserTopic(data.getId(), userIdes);

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Topic getById(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            return topicDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Topic> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return topicDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Topic> getUserTopics(int userId) throws InternalErrorException {
        try {
            return topicDao.getUserTopics(userId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void update(Topic data) throws InternalErrorException, DuplicateDataException, EntityNotFoundException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("title", data.getTitle());
            List<Topic> topics = topicDao.getByParams(params);


            if (topics != null && topics.size() > 0) {
                throw new DuplicateDataException();
            }

            topicDao.update(data);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void delete(int id, String path) throws InternalErrorException, EntityNotFoundException {
        try {
            Topic topic = topicDao.getById(id);

            dataStorage.setPath(path);
            dataStorage.remove(path, topic.getDataPath());
            topicDao.delete(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }
}
