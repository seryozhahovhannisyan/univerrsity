package com.university.web.action.general;

import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.IUserManager;
import com.university.model.account.User;
import com.university.model.account.lcp.Profile;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.struts2.interceptor.validation.SkipValidation;

public class Login extends ShellAction {

    private IUserManager userManager;

    private String email;
    private String password;

    @SkipValidation
    public String preLogin() {
        return SUCCESS;
    }

    public String execute() {
        try {

            User user = userManager.login(email, password, Language.ARMENIAN.getValue());

            if (user.getProfile() == Profile.ADMIN) {
            }

            storeInSession(ConstantGeneral.SESSION_USER, user);

            return user.getProfile().getName();
        } catch (InternalErrorException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (EntityNotFoundException e) {
            storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
            return INPUT;
        }
    }

    public void validate() {

        if (!CommonValidator.isEmailAddress(email)) {
            addFieldError("email", getText("errors.invalid"));
        }
        if (CommonValidator.isEmpty(password)) {
            addFieldError("password", getText("errors.invalid"));
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }
}
