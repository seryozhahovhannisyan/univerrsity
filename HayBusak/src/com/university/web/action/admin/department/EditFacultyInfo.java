package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;
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
public class EditFacultyInfo extends ShellAction {

    private IFacultyManager facultyManager;

    private int id;
    private Faculty faculty;
    private Language language;

    private String name;
    private String info;

    public void setFacultyManager(IFacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }

    @SkipValidation
    public String preExecute() {
        try {

            faculty = facultyManager.getById(id);
            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public String execute() {

        try {

            FacultyInfo facultyInfo = new FacultyInfo();

            facultyInfo.setFacultyId(id);
            facultyInfo.setName(name);
            facultyInfo.setInfo(info);
            facultyInfo.setLanguage(language);

            facultyManager.editInfo(facultyInfo);
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

        if (CommonValidator.isEmpty(info)) {
            addFieldError("info", getText("errors.internal"));
        }
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

    public void setInfo(String info) {
        this.info = info;
    }

    public Faculty getFaculty() {
        return faculty;
    }
}
