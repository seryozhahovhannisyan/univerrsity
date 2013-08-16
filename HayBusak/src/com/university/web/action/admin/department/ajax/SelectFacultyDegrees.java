package com.university.web.action.admin.department.ajax;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyDegreeManager;
import com.university.model.department.FacultyDegree;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.department.lcp.DocumentTypeInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ResponseDto;
import com.university.web.util.ResponseStatus;
import com.university.web.util.ShellAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 31.07.13
 * Time: 1:20
 * To change this template use File | Settings | File Templates.
 */
public class SelectFacultyDegrees extends ShellAction {

    private IFacultyDegreeManager degreeManager;

    private List<FacultyDegree> facultyDegrees;
    private ResponseDto responseDto;

    private int facultyId;
    private int degreeKey;

    public void setDegreeManager(IFacultyDegreeManager degreeManager) {
        this.degreeManager = degreeManager;
    }

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    @Override
    public String execute() {

        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("facultyId", facultyId);
        params.put("degreeKey", degreeKey);
        params.put("language", language);

        try {
            degreeManager.getByParams(params);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }
        return SUCCESS;
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

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setDegreeKey(int degreeKey) {
        this.degreeKey = degreeKey;
    }


}
