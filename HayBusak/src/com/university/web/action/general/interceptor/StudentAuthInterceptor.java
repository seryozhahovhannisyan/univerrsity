package com.university.web.action.general.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.university.model.account.User;
import com.university.model.account.lcp.Profile;
import com.university.web.util.ConstantGeneral;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 08.04.13
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */
public class StudentAuthInterceptor implements Interceptor {
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        User student = (User) session.get(ConstantGeneral.SESSION_USER);
        if (student == null || student.getProfile() != Profile.STUDENT) {
            return ActionSupport.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
