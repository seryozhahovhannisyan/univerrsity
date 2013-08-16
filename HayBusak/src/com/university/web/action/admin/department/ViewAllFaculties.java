package com.university.web.action.admin.department;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 27.07.13
 * Time: 22:34
 * To change this template use File | Settings | File Templates.
 */
public class ViewAllFaculties extends ShellAction {


    public String execute() {

        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return ERROR;
        }

        List<University> universities = null;
        try {
            universities = universityManager.getAllNames(language.getValue());
            storeInSession(ConstantGeneral.UNIVERSITIES, universities);

            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal.server"));
            return ERROR;
        }

    }

    private IUniversityManager universityManager;


    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }
}
