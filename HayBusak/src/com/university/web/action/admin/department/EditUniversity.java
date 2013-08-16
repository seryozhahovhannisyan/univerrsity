package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class EditUniversity extends ShellAction {

    private IUniversityManager universityManager;
    private static final Logger logger = Logger.getLogger(EditUniversity.class.getSimpleName());
    private int id;

    private File logo;
    private String logoFileName;
    private String logoContentType;

    private String phone;
    private String email;
    private String link;


    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }

    @Override
    public String execute() {

        try {

            List<University> universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);

            lab:
            for (int i = 0; i < universities.size(); i++) {

                if (universities.get(i).getId() == id) {

                    University university = universities.get(i);

                    if (!CommonValidator.isEmpty(phone)) {
                        university.setPhone(phone);
                    }

                    if (!CommonValidator.isEmpty(link)) {
                        university.setLink(link);
                    }

                    if (CommonValidator.isEmailAddress(email)) {
                        university.setEmail(email);
                    }

                    universityManager.edit(university, logo, DATA_FILE_PATH, logoFileName);

                    universities.set(i, university);
                    break lab;
                }
            }


            storeInSession(ConstantGeneral.INFO, getText("info.edited"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to edit University data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (EntityNotFoundException e) {
            logger.error(String.format("unable to edit University data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
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

    public void setLink(String link) {
        this.link = link;
    }
}
