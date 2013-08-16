package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IDepartmentManager;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.struts2.interceptor.validation.SkipValidation;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class EditDepartmentInfo extends ShellAction {

    private IDepartmentManager departmentManager;

    public void setDepartmentManager(IDepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    private int id;
    private Department department;
    private Language language;

    private String name;

    @SkipValidation
    public String preExecute() {
        try {
            department = departmentManager.getById(id);
            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public String execute() {

        try {

            DepartmentInfo info = new DepartmentInfo();

            info.setDepartmentId(id);
            info.setName(name);
            info.setLanguage(language);

            departmentManager.editInfo(info);
            storeInSession(ConstantGeneral.INFO, getText("info.edited"));
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

        if (CommonValidator.isEmpty(name)) {
            addFieldError("name", getText("errors.internal"));
        }
    }

    public Department getDepartment() {
        return department;
    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void setLanguage(String value) {
        try {
            int langValue = Integer.parseInt(value);
            language = Language.valueOf(langValue);
        } catch (NumberFormatException e) {
            addFieldError("language", getText("errors.invalid"));
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
