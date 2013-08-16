package com.university.web.action.admin.department;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IDepartmentManager;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;
import com.university.model.department.University;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 18:06
 * To change this template use File | Settings | File Templates.
 */
public class AddDepartment extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddDepartment.class.getSimpleName());
    private IDepartmentManager departmentManager;

    List<University> universities;

    private int universityId;
    private List<String> names;

    public void setDepartmentManager(IDepartmentManager departmentManager) {
        this.departmentManager = departmentManager;
    }

    @SkipValidation
    public String preExecute() {
        return SUCCESS;
    }

    public String execute() {

        Department department = new Department();

        universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);

        lab:
        for(University university :universities){
           if(university.getId() == universityId){
               university.setId(universityId);
               department.setUniversity(university);
               break lab;
           }
        }

        try {

            List<DepartmentInfo> infos = new ArrayList<DepartmentInfo>();
            for (int i = 0; i < names.size(); i++) {

                DepartmentInfo info = new DepartmentInfo();

                info.setLanguage(Language.values()[i]);
                info.setName(names.get(i));

                infos.add(info);
            }

            department.setDepartmentInfos(infos);

            departmentManager.add(department);

            storeInSession(ConstantGeneral.INFO, getText("info.added"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add Department data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add Department data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }

    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }
}
