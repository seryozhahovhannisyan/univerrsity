package com.university.web.action.admin.account.subject;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ISubjectManager;
import com.university.model.account.Subject;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 1:34
 * To change this template use File | Settings | File Templates.
 */
public class DeleteSubject extends ShellAction {

    private static final Logger logger = Logger.getLogger(DeleteSubject.class.getSimpleName());
    private ISubjectManager subjectManager;

    public void setSubjectManager(ISubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    private int id;
    private List<Subject> subjects;

    public String execute(){

        try {
            subjectManager.delete(id);
            subjects = subjectManager.getByParams(null);
            storeInSession(ConstantGeneral.SUBJECTS,subjects);
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to delete subject data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.invalid.data"));
            return ERROR;
        }  catch (EntityNotFoundException e) {
            logger.error(String.format("unable to delete subject data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.invalid.data"));
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if(!CommonValidator.isPositiveNumber(""+id)){
            logger.error(String.format("The id is required data [%s]"));
            storeInSession(ConstantGeneral.ERR_MESSAGE,getText("errors.invalid.data"));
        }
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setId(int id) {
        this.id = id;
    }
}
