package com.university.web.action.admin.department;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyDegreeManager;
import com.university.model.department.*;
import com.university.model.department.lcp.AcademicDegree;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.department.lcp.DocumentType;
import com.university.model.department.lcp.DocumentTypeInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 27.07.13
 * Time: 13:46
 * To change this template use File | Settings | File Templates.
 */
public class AddFacultyDegree extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddFacultyDegree.class.getSimpleName());

    private int universityId;
    private int departmentId;
    private int facultyId;
    private int degreeKey;

    private List<File> dataList;
    private List<String> dataListFileName;
    private List<String> dataListsContentType;

    private String[] serialNumbers;
    private Date[] confirmDates;

    private String[] abouts;
    private int[] docTypeKeys;

    @SkipValidation
    public String preExecute() {
        return SUCCESS;
    }

    @Override
    public String execute() {

        try {

            List<FacultyDegree> facultyDegrees = new ArrayList<FacultyDegree>();
            //List<Document> documents = new ArrayList<Document>();

            int aboutIndex = 0;
            for (int i = 0; i < serialNumbers.length; i++) {

                Document document = new Document();

                document.setSerialNumber(serialNumbers[i]);
                document.setDocumentType(DocumentType.valueOf(docTypeKeys[i]));
                document.setConfirmDate(confirmDates[i]);

                List<DocumentInfo> infos = new ArrayList<DocumentInfo>();


                for (int j = 1; j <= Language.values().length; j++) {
                    DocumentInfo info = new DocumentInfo();
                    info.setAbout(abouts[j - 1 + aboutIndex]);
                    info.setLanguage(Language.valueOf(j));
                    infos.add(info);
                }
                aboutIndex += Language.values().length;
                document.setDocumentInfos(infos);
                //documents.add(document);

                FacultyDegree facultyDegree = new FacultyDegree();

                Faculty faculty = new Faculty();
                faculty.setId(facultyId);

                facultyDegree.setFaculty(faculty);
                facultyDegree.setDegree(AcademicDegree.valueOf(degreeKey));
                facultyDegree.setDocument(document);

                facultyDegrees.add(facultyDegree);
            }
            List<University> universities = (List<University>) getFromSession(ConstantGeneral.UNIVERSITIES);
            //path generate
            String universityPcg="";
            lab:
            for (University university : universities) {
                if (university.getId() == universityId) {
                    universityPcg = university.getPhone();
                    break lab;
                }
            }

            List<String> pcgNames = new ArrayList<String>();
            pcgNames.add(universityPcg);
            pcgNames.add(ConstantGeneral.PACKAGE_FACULTY ) ;
            pcgNames.add(""+departmentId);
            pcgNames.add(ConstantGeneral.PACKAGE_DOCUMENTS);

            degreeManager.add(facultyDegrees, dataList,DATA_FILE_PATH , pcgNames, dataListFileName);
            storeInSession(ConstantGeneral.INFO, getText("info.added"));
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add Faculty Document data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add Faculty Document data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.internal"));
            return ERROR;
        }
    }

    @Override
    public void validate() {

        int requiredLength = DocumentType.values().length;

        if (CommonValidator.isEmpty(dataList) || dataList.size() != requiredLength) {
            logger.error(String.format("unable to add Faculty Document data as logos is null"));
            addFieldError("logo", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(serialNumbers) || serialNumbers.length != requiredLength) {
            logger.error(String.format("unable to add Faculty Document data as serialNumbers is null"));
            addFieldError("serialNumbers", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(confirmDates) || confirmDates.length != requiredLength) {
            logger.error(String.format("unable to add Faculty Document data as confirmDates is null"));
            addFieldError("confirmDates", getText("errors.required"));
        }

        if (CommonValidator.isEmpty(abouts) ) {//todo count the required list size and validate them
            logger.error(String.format("unable to add Faculty Document data as abouts is null"));
            addFieldError("abouts", getText("errors.required"));
        }

        if (docTypeKeys == null) {
            logger.error(String.format("unable to add Faculty Document data as docTypeKeys is null"));
            addFieldError("docTypeKeys", getText("errors.required"));
        }
    }

    public List<AcademicDegreeInfo> getDegreeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return AcademicDegreeInfo.listValueOfLanguage(language);
    }

    public List<DocumentTypeInfo> getDocumentTypeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return DocumentTypeInfo.listValueOfLanguage(language);
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setDegreeKey(int degreeKey) {
        this.degreeKey = degreeKey;
    }

    public void setLanguage(String value) {
        try {
            int langValue = Integer.parseInt(value);
            //language = Language.valueOf(langValue);
        } catch (NumberFormatException e) {
            addFieldError("language", getText("errors.invalid"));
        }
    }

    public void setDataList(List<File> dataList) {
        this.dataList = dataList;
    }

    public void setDataListFileName(List<String> dataListFileName) {
        this.dataListFileName = dataListFileName;
    }

    public void setDataListsContentType(List<String> dataListsContentType) {
        this.dataListsContentType = dataListsContentType;
    }

    public void setSerialNumbers(String[] serialNumbers) {
        this.serialNumbers = serialNumbers;
    }

    public void setConfirmDates(Date[] confirmDates) {
        this.confirmDates = confirmDates;
    }

    public void setAbouts(String[] abouts) {
        this.abouts = abouts;
    }

    public void setDocTypeKeys(int[] docTypeKeys) {
        this.docTypeKeys = docTypeKeys;
    }

    public Language[] getLanguages() {
        return Language.values();
    }

    private IFacultyDegreeManager degreeManager;

    public void setDegreeManager(IFacultyDegreeManager degreeManager) {
        this.degreeManager = degreeManager;
    }

}
