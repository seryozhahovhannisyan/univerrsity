package com.university.web.action.admin.account.topic;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ITopicManager;
import com.university.model.account.Topic;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 09.08.13
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class ViewAllTopics extends ShellAction {

    private static final Logger logger = Logger.getLogger(ViewAllTopics.class.getSimpleName());
    private ITopicManager topicManager;

    private List<Topic> topics;

    @Override
    public String execute() {
        try {
            topics = topicManager.getByParams(null);
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to load users data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal.server"));
            return INPUT;
        }

    }

    public void setTopicManager(ITopicManager topicManager) {
        this.topicManager = topicManager;
    }

    public List<Topic> getTopics() {
        return topics;
    }
}
