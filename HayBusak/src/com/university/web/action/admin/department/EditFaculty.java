package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.model.department.Faculty;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class EditFaculty extends ShellAction {

    private IFacultyManager facultyManager;

    private int id;

    private File logo;
    private String logoFileName;
    private String logoContentType;

    private String phone;
    private String email;

    public void setFacultyManager(IFacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }

    @Override
    public String execute() {

        try {
            Faculty faculty = new Faculty();

            faculty.setId(id);
            faculty.setPhone(phone);
            faculty.setEmail(email);

            facultyManager.edit(faculty, logo, DATA_FILE_PATH, logoFileName);
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
        if (!CommonValidator.isPositiveNumber("" + id)) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
        }

        if (CommonValidator.isEmpty(logoFileName)) {
            addFieldError("logoFile", getText("errors.internal"));
        }

        if (CommonValidator.isEmpty(phone)) {
            addFieldError("phone", getText("errors.internal"));
        }

        if (!CommonValidator.isEmailAddress(email)) {
            addFieldError("email", getText("errors.internal"));
        }
    }

    public void setId(int id) {
        this.id = id;
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

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
