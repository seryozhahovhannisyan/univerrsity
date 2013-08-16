package com.university.web.action.admin.department.ajax;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.model.department.Faculty;
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
 * Date: 27.07.13
 * Time: 20:46
 * To change this template use File | Settings | File Templates.
 */
public class SelectDepartment extends ShellAction {
    //for ajax
    private int departmentId;
    private List<Faculty> faculties;

    @Override
    public String execute() throws Exception {

        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }

        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("langId", language.getValue());
            params.put("departmentId", departmentId);

            faculties = facultyManager.getByParams(params);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }
        return SUCCESS;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    private ResponseDto responseDto;
    private IFacultyManager facultyManager;

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public void setFacultyManager(IFacultyManager facultyManager) {
        this.facultyManager = facultyManager;
    }
}
