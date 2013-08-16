package com.university.web.util;

/**
 * User: Seryozha Hovhannisyan
 * Date: 6/6/13
 * Time: 3:36 PM
 */
public class ConstantGeneral {
    //Languages
    public static final String REQUEST_LANGUAGE = "lang";
    public static final String SESSION_LANGUAGE = "WW_TRANS_I18N_LOCALE";
    public static final String REQUEST_LOCALE = "request_locale";   // for session
    public static final String REQUEST_ONLY_LOCALE = "request_only_locale"; // for the current request only (ignores struts solution for 'request_only_locale')
    //Files
    public static final String MAIN_PATH = "/data";
    public static final String FILE_SEPARATOR = "/";
    public static final String PACKAGE_FACULTY = "faculty";
    public static final String PACKAGE_DOCUMENTS = "documents";

    public static final String PACKAGE_DIPLOMA = "diploma";
    public static final String PACKAGE_AWARD = "award";
    public static final String PACKAGE_ACCREDITATION = "accreditation";
    //
    public static final String PACKAGE_COLLEGE="College";
    public static final String PACKAGE_BACHELOR="Bachelor";
    public static final String PACKAGE_MAGISTRACY="magistracy";
    public static final String PACKAGE_CERTIFICATE="Certificate";
    //messages
    public static final String INFO = "info";
    public static final String ERR_MESSAGE = "err_message";
    //The keywords for session,request and interceptor parameters
    public static final String LANGUAGE = "lang";
    public static final String USER = "user";
    public static final String ADMIN = "admin";
    public static final String USERS = "users";
    public static final String SESSION_USER = "session_user";
    public static final String SUBJECTS = "subjects";


    //The database fields for search
    /**
     * Session scope variables
     */
    public static final String UNIVERSITIES = "universities";
}
