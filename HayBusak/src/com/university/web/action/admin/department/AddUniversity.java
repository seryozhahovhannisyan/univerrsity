package com.university.web.action.admin.department;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 1:30
 * To change this template use File | Settings | File Templates.
 */
public class AddUniversity extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddUniversity.class.getSimpleName());

    private File logo;
    private String logoFileName;
    private String logoContentType;

    private String phone;
    private String email;
    private String link;

    private List<String> names;
    private List<String> abbreviations;
    private List<String> addresses;
    private List<String> infos;

    private Language language;

    @SkipValidation
    public String preExecute() {
        return SUCCESS;
    }

    public String execute() {

        University university = new University();

        university.setPhone(phone);
        university.setEmail(email);
        university.setLink(link);

        List<UniversityInfo> universityInfos = new ArrayList<UniversityInfo>();

        for (int i = 0; i < names.size(); i++) {

            UniversityInfo universityInfo = new UniversityInfo();

            Language currentLanguage = (Language)getFromSession(ConstantGeneral.LANGUAGE);
            lab:
            if(currentLanguage.getValue() == Language.values()[i].getValue()){
                university.setName(names.get(i));
                university.setAbbreviation(abbreviations.get(i));
                university.setAddress(addresses.get(i));
                university.setInfo(infos.get(i));
                university.setLanguage(Language.values()[i]);
                break lab;
            }

            universityInfo.setName(names.get(i));
            universityInfo.setAbbreviation(abbreviations.get(i));
            universityInfo.setAddress(addresses.get(i));
            universityInfo.setInfo(infos.get(i));
            universityInfo.setLanguage(Language.values()[i]);

            universityInfos.add(universityInfo);
        }

        university.setUniversityInfos(universityInfos);

        try {
            universityManager.add(university, logo, DATA_FILE_PATH, logoFileName);


            List<University> universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);
            universities.add(university);

            storeInSession(ConstantGeneral.INFO, getText("info.added"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add University data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add University data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }

    }

    @Override
    public void validate() {

        /*if (CommonValidator.isEmpty(logoFileName)) {
            addFieldError("logo", getText("errors.required"));
        }*/

        if (CommonValidator.isEmpty(phone)) {
            logger.error(String.format("invalid phone [%s]", phone));
            addFieldError("phone", getText("errors.required"));
        }

        if (!CommonValidator.isEmailAddress(email)) {
            logger.error(String.format("invalid email [%s]", email));
            addFieldError("email", getText("errors.invalid.email"));
        }

        if (CommonValidator.isEmpty(link)) {
            logger.error(String.format("invalid link [%s]", link));
            addFieldError("link", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(names)) {
            logger.error(String.format("invalid names [%s]", names));
            addFieldError("names", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(abbreviations)) {
            logger.error(String.format("invalid abbreviations [%s]", abbreviations));
            addFieldError("abbreviations", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(addresses)) {
            logger.error(String.format("invalid addresses [%s]", addresses));
            addFieldError("addresses", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(infos)) {
            logger.error(String.format("invalid infos [%s]", infos));
            addFieldError("infos", getText("errors.required"));
        }

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

    public void setNames(List<String> names) {
        this.names = names;
    }

    public void setAbbreviations(List<String> abbreviations) {
        this.abbreviations = abbreviations;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    public void setInfos(List<String> infos) {
        this.infos = infos;
    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void setLanguage(String value) {
        try {
            int langValue = Integer.parseInt(value);
            language = Language.valueOf(langValue);
        } catch (NumberFormatException e) {
            logger.error(String.format("unable to parse lang key to language [%s]", e));
            addFieldError("language", getText("errors.invalid"));
        }
    }

    private IUniversityManager universityManager;

    public void setUniversityManager(IUniversityManager universityManager) {
        this.universityManager = universityManager;
    }
}
