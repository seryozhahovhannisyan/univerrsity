package com.university.web.action.admin.account.topic;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ITopicManager;
import com.university.data_access.service.account.IUserManager;
import com.university.model.account.GroupSubject;
import com.university.model.account.Topic;
import com.university.model.account.User;
import com.university.model.account.lcp.AcademicDegreeCourse;
import com.university.model.account.lcp.Profile;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 09.08.13
 * Time: 15:54
 * To change this template use File | Settings | File Templates.
 */
public class AddTopic extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddTopic.class.getSimpleName());
    private ITopicManager topicManager;

    //private List<User> users;

    private List<Integer> userIdes;
    private List<Integer>groupSubjectIdes;

    private String title;
    private Date creationDate;

    private File data;
    private String dataFileName;
    private String dataContentType;

    @SkipValidation
    public String preExecute() {
       return SUCCESS;
    }

    @Override
    public String execute() {

        for(int groupSubjectId : groupSubjectIdes){
            GroupSubject groupSubject = new GroupSubject();
            groupSubject.setId(groupSubjectId);

            Topic topic = new Topic();
            topic.setTitle(title);
            topic.setCreationDate(creationDate);
            topic.setGroupSubject(groupSubject);

            try {
                topicManager.add(topic,userIdes,data,DATA_FILE_PATH,dataFileName);
            } catch (InternalErrorException e) {
                logger.error(String.format("unable to add Topic data [%s]", e.getMessage()));
                storeInSession(ConstantGeneral.ERR_MESSAGE,getText("errors.invalid.data"));
                return ERROR;
            } catch (DuplicateDataException e) {
                logger.error(String.format("unable to add Topic data [%s]", e.getMessage()));
                storeInSession(ConstantGeneral.ERR_MESSAGE,getText("errors.invalid.data"));
                return INPUT;
            }
        }

        return SUCCESS;
    }

    @Override
    public void validate() {

    }

    public AcademicDegreeCourse[] getCourses() {
        return AcademicDegreeCourse.values();
    }

    public List<AcademicDegreeInfo> getDegreeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return AcademicDegreeInfo.listValueOfLanguage(language);
    }

    public void setUserIdes(List<Integer> userIdes) {
        this.userIdes = userIdes;
    }

    public void setGroupSubjectIdes(List<Integer> groupSubjectIdes) {
        this.groupSubjectIdes = groupSubjectIdes;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setData(File data) {
        this.data = data;
    }

    public void setDataFileName(String dataFileName) {
        this.dataFileName = dataFileName;
    }

    public void setDataContentType(String dataContentType) {
        this.dataContentType = dataContentType;
    }

    public void setTopicManager(ITopicManager topicManager) {
        this.topicManager = topicManager;
    }
}
