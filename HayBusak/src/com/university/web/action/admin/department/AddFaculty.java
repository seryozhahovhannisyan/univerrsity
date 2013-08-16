package com.university.web.action.admin.department;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.model.department.Department;
import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;
import com.university.model.department.University;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 27.07.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class AddFaculty extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddFaculty.class.getSimpleName());

    private File logo;
    private String logoFileName;
    private String logoContentType;

    private int universityId;
    private int departmentId;
    private String phone;
    private String email;

    private List<String> names;
    private List<String> infos;


    @SkipValidation
    public String preExecute() {
        return SUCCESS;
    }


    @Override
    public String execute() {


        Department department = new Department();
        department.setId(departmentId);

        List<University> universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);
        lab:
        for (University university : universities) {
            if (university.getId() == universityId) {
                department.setUniversity(university);
                break lab;
            }
        }


        Faculty faculty = new Faculty();
        faculty.setDepartment(department);
        faculty.setPhone(phone);
        faculty.setEmail(email);

        List<FacultyInfo> facultyInfos = new ArrayList<FacultyInfo>();
        for (int i = 0; i < names.size(); i++) {

            FacultyInfo facultyInfo = new FacultyInfo();

            facultyInfo.setName(names.get(i));
            facultyInfo.setInfo(infos.get(i));
            facultyInfo.setLanguage(Language.values()[i]);

            facultyInfos.add(facultyInfo);
        }

        faculty.setFacultyInfos(facultyInfos);

        try {
            facultyManager.add(faculty, logo, DATA_FILE_PATH, logoFileName);
            storeInSession(ConstantGeneral.INFO, getText("info.added"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add Faculty data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add Faculty data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public void validate() {

        if (CommonValidator.isEmpty(logoFileName)) {
            logger.error(String.format("unable to add Faculty invalid data [%s]", logoFileName));
            addFieldError("logo", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(phone)) {
            logger.error(String.format("unable to add Faculty invalid data [%s]", phone));
            addFieldError("phone", getText("errors.required"));
        }

        if (!CommonValidator.isEmailAddress(email)) {
            logger.error(String.format("unable to add Faculty invalid data [%s]", email));
            addFieldError("email", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(names)) {
            logger.error(String.format("unable to add Faculty invalid data [%s]", names));
            addFieldError("names", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(infos)) {
            logger.error(String.format("unable to add Faculty invalid data [%s]", infos));
            addFieldError("infos", getText("errors.required"));
        }
    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void setLogo(File logo) {
        this.logo = logo;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }

    public void setLogoContentType(String logoContentType) {
        this.logoContentType = logoContentType;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }

    private IFacultyManager facultyManager;


    public void setFacultyManager(IFacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }
}
