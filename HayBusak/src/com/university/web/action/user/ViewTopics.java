package com.university.web.action.user;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ITopicManager;
import com.university.model.account.Topic;
import com.university.model.account.User;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 12.08.13
 * Time: 0:48
 * To change this template use File | Settings | File Templates.
 */
public class ViewTopics  extends ShellAction {

    private static final Logger logger = Logger.getLogger(ViewTopics.class.getSimpleName());

    private ITopicManager topicManager;
    private List<Topic> topics;

    @Override
    public String execute(){

        User user = (User)getFromSession(ConstantGeneral.SESSION_USER);
        try {
            if(user!= null){
                topics = topicManager.getUserTopics(user.getId());
            }   else {
                topics = topicManager.getUserTopics(1);//todo for test
            }

            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to load user topics data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE,"unable to load user topics data");
            return INPUT;
        }
    }

    public List<Topic> getTopics() {
        return topics;
    }

    public void setTopicManager(ITopicManager topicManager) {
        this.topicManager = topicManager;
    }
}
