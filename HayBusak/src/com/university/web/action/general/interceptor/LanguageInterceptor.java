package com.university.web.action.general.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import java.util.Map;

public class LanguageInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation actionInvocation) throws Exception {

        ActionContext actionContext = actionInvocation.getInvocationContext();

        Map<String, Object> session = actionContext.getSession();
        Map<String, Object> parameters = actionContext.getParameters();

        Cookie[] cookies = ServletActionContext.getRequest().getCookies();

        // if any parameters bellow mentioned exist, it means employer is changing the resource
        if (parameters.containsKey(ConstantGeneral.REQUEST_LOCALE) ||
                parameters.containsKey(ConstantGeneral.REQUEST_ONLY_LOCALE) ||
                parameters.containsKey(ConstantGeneral.REQUEST_LANGUAGE)) {

            String language;
            if (parameters.containsKey(ConstantGeneral.REQUEST_LOCALE)) {
                language = ((String[]) parameters.get(ConstantGeneral.REQUEST_LOCALE))[0];
            } else if (parameters.containsKey(ConstantGeneral.REQUEST_ONLY_LOCALE)) {
                language = ((String[]) parameters.get(ConstantGeneral.REQUEST_ONLY_LOCALE))[0];
            } else {
                language = ((String[]) parameters.get(ConstantGeneral.REQUEST_LANGUAGE))[0];
            }

            // retrieves resource
            Language lang = Language.languageOf(language);
            setLanguage(session, lang, true);
            return actionInvocation.invoke();
        }

        // retrieves employer preferred resource if it's defined
        if (cookies != null && cookies.length > 0) {

            for (Cookie cookie : cookies) {
                if (cookie.getName().equalsIgnoreCase(ConstantGeneral.LANGUAGE)) {

                    String language = cookie.getValue();
                    // gets 'Language' via resource
                    Language lang = Language.languageOf(language);
                    setLanguage(session, lang, false);
                    return actionInvocation.invoke();
                }
            }
        }

        // stores default resource in session and cookie if it doesn't exist
        if (session.isEmpty() || !session.containsKey(ConstantGeneral.LANGUAGE)) {

            // gets default resource
            Language lang = Language.getDefault();
            setLanguage(session, lang, true);
        }

        return actionInvocation.invoke();
    }

    /**
     * Stores chosen resource into session and cookie,
     * if resource exists in any storage it will be replaced.
     *
     * @param session        (session mapped data)
     * @param lang           (chosen resource)
     * @param storeInCookies
     */
    private void setLanguage(Map<String, Object> session, Language lang, boolean storeInCookies) {

        Language sesLang = (Language) session.get(ConstantGeneral.LANGUAGE);

        // if resource not stored in session
        // or it is not matched with chosen resource
        if (sesLang == null || sesLang != lang) {
            // sets resource for employer usage
            session.put(ConstantGeneral.LANGUAGE, lang);
            // sets locale for application localization (i18n)
            session.put(ConstantGeneral.SESSION_LANGUAGE, lang.getLocale());
            ActionContext.getContext().setLocale(lang.getLocale());
        }

        if (storeInCookies) {
            // add resource cookie
            Cookie cookie = new Cookie(ConstantGeneral.LANGUAGE, lang.getLocale().getLanguage());
            cookie.setMaxAge(365*24*60*60);
            ServletActionContext.getResponse().addCookie(cookie);
        }
    }
}
