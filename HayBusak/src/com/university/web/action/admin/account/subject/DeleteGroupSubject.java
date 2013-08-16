package com.university.web.action.admin.account.subject;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 09.08.13
 * Time: 15:29
 * To change this template use File | Settings | File Templates.
 */
public class DeleteGroupSubject extends ShellAction {

    private static final Logger logger = Logger.getLogger(DeleteGroupSubject.class.getSimpleName());
    private IGroupSubjectManager groupSubjectManager;
    private int id;


    @Override
    public String execute() {
        try {
            groupSubjectManager.delete(id);
            storeInSession(ConstantGeneral.INFO, getText("info.deleted"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to delete group's subject", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, "unable to delete group's subject");
            return ERROR;
        } catch (EntityNotFoundException e) {
            logger.error(String.format("unable to delete group's subject", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, "unable to delete group's subject");
            return ERROR;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupSubjectManager(IGroupSubjectManager groupSubjectManager) {
        this.groupSubjectManager = groupSubjectManager;
    }
}
