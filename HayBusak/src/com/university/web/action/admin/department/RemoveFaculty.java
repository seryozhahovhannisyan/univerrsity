package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class RemoveFaculty extends ShellAction {

    private IFacultyManager facultyManager;
    private int id;

    public void setFacultyManager(IFacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }

    @Override
    public String execute() {

        try {
            facultyManager.remove(id, DATA_FILE_PATH);
            storeInSession(ConstantGeneral.INFO, getText("info.deleted"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (EntityNotFoundException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (!CommonValidator.isPositiveNumber("" + id)) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
        }
    }

    public void setId(int id) {
        this.id = id;
    }
}
