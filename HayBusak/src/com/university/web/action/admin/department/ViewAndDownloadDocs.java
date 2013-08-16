package com.university.web.action.admin.department;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyDegreeManager;
import com.university.model.department.FacultyDegree;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.department.lcp.DocumentType;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class ViewAndDownloadDocs extends ShellAction {

    private static final Logger logger = Logger.getLogger(ViewAndDownloadDocs.class.getSimpleName());

    private List<FacultyDegree> facultyDegrees;

    private int facultyId;
    private int degreeKey;

    @Override
    public String execute() {
        try {

            Language language = (Language)getFromSession(ConstantGeneral.LANGUAGE);

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("langId", language.getValue());
            //params.put("docTypeId", DocumentType.DIPLOMA.getId());

            facultyDegrees = degreeManager.getByParams(params);
            return SUCCESS;
        } catch (InternalErrorException e) {
            return ERROR;
        }
    }

    public List<AcademicDegreeInfo> getDegreeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return AcademicDegreeInfo.listValueOfLanguage(language);
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setDegreeKey(int degreeKey) {
        this.degreeKey = degreeKey;
    }

    private IFacultyDegreeManager degreeManager;

    public void setDegreeManager(IFacultyDegreeManager degreeManager) {
        this.degreeManager = degreeManager;
    }

    public List<FacultyDegree> getFacultyDegrees() {
        return facultyDegrees;
    }
}
