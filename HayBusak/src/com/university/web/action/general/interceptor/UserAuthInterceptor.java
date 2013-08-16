package com.university.web.action.general.interceptor;


import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.university.model.account.User;
import com.university.model.account.lcp.Profile;
import com.university.web.util.ConstantGeneral;

import java.util.Map;


public class UserAuthInterceptor implements Interceptor {
    @Override
    public void destroy() {
    }

    @Override
    public void init() {
    }

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        Map<String, Object> session = actionInvocation.getInvocationContext().getSession();
        User user = (User)session.get(ConstantGeneral.SESSION_USER);
        if(user == null || user.getProfile() != Profile.USER){
            return ActionSupport.LOGIN;
        }
        return actionInvocation.invoke();
    }
}
