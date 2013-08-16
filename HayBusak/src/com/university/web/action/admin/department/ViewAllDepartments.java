package com.university.web.action.admin.department;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IDepartmentManager;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.Department;
import com.university.model.department.University;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class ViewAllDepartments extends ShellAction {

    private IDepartmentManager departmentManager;
    private IUniversityManager universityManager;

    private List<Department> departments;


    public void setDepartmentManager(IDepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }

    @Override
    public String execute() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return ERROR;
        }

        try {

            List<University> universities = universityManager.getAllNames(language.getValue());
            storeInSession(ConstantGeneral.UNIVERSITIES, universities);
            //}

            departments = departmentManager.getAll(language.getValue());

            return SUCCESS;
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
