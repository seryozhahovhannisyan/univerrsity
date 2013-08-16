package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IDepartmentManager;
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
public class RemoveDepartment extends ShellAction {

    private IDepartmentManager departmentManager;
    private int id;


    public void setDepartmentManager(IDepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    @Override
    public String execute() {

        try {
            departmentManager.remove(id);
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
