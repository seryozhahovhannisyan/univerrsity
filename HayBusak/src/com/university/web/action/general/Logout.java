package com.university.web.action.general;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;

/**
 * User: Seryozha Hovhannisyan
 * Date: 6/6/13
 * Time: 3:16 PM
 */
public class Logout extends ShellAction {

    public String execute(){
        //storeInSession(ConstantGeneral.USER, null);
        getHHttpSession().invalidate();
        return SUCCESS;
    }

}
