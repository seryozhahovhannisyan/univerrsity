package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;
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
public class EditUniversityInfo extends ShellAction {

    private IUniversityManager universityManager;

    private int id;
    private University university;
    private Language language;

    private String name;
    private String abbreviation;
    private String address;
    private String info;


    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }

    @SkipValidation
    public String preExecute() {
        try {
            university = universityManager.getById(id);
            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public String execute() {

        try {

            UniversityInfo universityInfo = new UniversityInfo();

            universityInfo.setUniversityId(id);
            universityInfo.setName(name);
            universityInfo.setAbbreviation(abbreviation);
            universityInfo.setAddress(address);
            universityInfo.setInfo(info);
            universityInfo.setLanguage(language);

            universityManager.editInfo(universityInfo);
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

        if (CommonValidator.isEmpty(abbreviation)) {
            addFieldError("abbreviation", getText("errors.internal"));
        }

        if (CommonValidator.isEmpty(info)) {
            addFieldError("info", getText("errors.internal"));
        }

        if (CommonValidator.isEmpty(address)) {
            addFieldError("address", getText("errors.internal"));
        }
    }

    public University getUniversity() {
        return university;
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

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
