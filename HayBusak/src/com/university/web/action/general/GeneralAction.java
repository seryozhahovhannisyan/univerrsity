package com.university.web.action.general;

import com.opensymphony.xwork2.ActionContext;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.model.department.lcp.AcademicDegree;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class GeneralAction extends ShellAction {

    private IUniversityManager universityManager;
    private static final Logger logger = Logger.getLogger(GeneralAction.class.getSimpleName());

    public String execute() {

        Object o = getFromSession(ConstantGeneral.UNIVERSITIES);

        if (o == null) {
            Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);

            if (language == null) {

                language = Language.ARMENIAN;
                // sets resource for employer usage
                storeInSession(ConstantGeneral.LANGUAGE, language);
                // sets locale for application localization (i18n)
                storeInSession(ConstantGeneral.SESSION_LANGUAGE, language.getLocale());
                ActionContext.getContext().setLocale(language.getLocale());
            }

            try {
                List<University> universities = universityManager.getAll(language.getValue());
                storeInSession(ConstantGeneral.UNIVERSITIES, universities);
            } catch (InternalErrorException e) {
                storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
                logger.error(String.format("unable to get all University data [%s]", language.getKey()));
                return ERROR;
            }
        }

        return SUCCESS;
    }

    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }
}
