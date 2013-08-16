package com.university.web.util;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.university.model.general.lcp.Language;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ShellAction extends ActionSupport implements SessionAware, RequestAware {

    private Map<String, Object> request;
    private Map<String, Object> session;

    public static final String DATA_FILE_PATH;

    static {
        DATA_FILE_PATH = ServletActionContext.getServletContext().getRealPath(ConstantGeneral.MAIN_PATH);
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }


    //store block
    public void storeInSession(String key, Object value) {
        session.put(key, value);
    }

    public void storeInRequest(String key, Object value) {
        request.put(key, value);
    }

    //getter block
    public Object getFromSession(String key) {
        return session.get(key);
    }

    public Object getFromRequest(String key) {
        return request.get(key);
    }

    public HttpSession getHHttpSession() {
        return ServletActionContext.getRequest().getSession();
    }
}

