package com.university.web.action.admin.account.subject;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ISubjectManager;
import com.university.model.account.Subject;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class AddSubject extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddSubject.class.getSimpleName());
    private ISubjectManager subjectManager;

    public void setSubjectManager(ISubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    private String name;
    private List<Subject> subjects;

    @SkipValidation
    public String preExecute(){
        return SUCCESS;
    }

    public String execute(){

        Subject subject = new Subject();
        subject.setName(name);

        try {
            subjectManager.add(subject);
            subjects = subjectManager.getByParams(null);
            storeInSession(ConstantGeneral.SUBJECTS,subjects);
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add subject data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.invalid.data"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add subject data as exists [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.invalid.data"));
            return INPUT;
        }
    }

    @Override
    public void validate() {
        if(CommonValidator.isEmpty(name)){
            addFieldError("name",getText("errors.required"));
            logger.error(String.format("The name is required data "));
            storeInSession(ConstantGeneral.ERR_MESSAGE,getText("errors.required"));
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setName(String name) {
        this.name = name;
    }
}
