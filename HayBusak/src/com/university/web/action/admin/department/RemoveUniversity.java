package com.university.web.action.admin.department;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 26.07.13
 * Time: 2:36
 * To change this template use File | Settings | File Templates.
 */
public class RemoveUniversity extends ShellAction {

    private static final Logger logger = Logger.getLogger(RemoveUniversity.class.getSimpleName());
    private IUniversityManager universityManager;
    private int id;


    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }

    @Override
    public String execute() {

        try {
            List<University> universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);
            University university = null;

            lab:
            for (int i = 0; i < universities.size(); i++) {
                if (universities.get(i).getId() == id) {
                    university = universities.get(i);
                    universities.remove(i);
                    universityManager.remove(university, DATA_FILE_PATH);
                    storeInSession(ConstantGeneral.INFO, getText("info.deleted"));
                    break lab;
                }
            }

            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to remove University data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (EntityNotFoundException e) {
            logger.error(String.format("unable to remove University data [%s]", e.getMessage()));
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
