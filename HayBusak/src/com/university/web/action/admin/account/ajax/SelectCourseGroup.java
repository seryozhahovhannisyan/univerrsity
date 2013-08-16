package com.university.web.action.admin.account.ajax;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.data_access.service.department.IDepartmentManager;
import com.university.model.account.CourseGroup;
import com.university.model.account.GroupSubject;
import com.university.model.department.Department;
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
public class SelectCourseGroup extends ShellAction {
    //for ajax
    private int courseId;
    private int facultyId;
    private List<CourseGroup> courseGroups;

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
            params.put("courseId", courseId);
            params.put("facultyId", facultyId);

            courseGroups = courseGroupManager.getByParams(params);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }
        return SUCCESS;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public List<CourseGroup> getCourseGroups() {
        return courseGroups;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    private ResponseDto responseDto;
    private ICourseGroupManager courseGroupManager;

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public void setCourseGroupManager(ICourseGroupManager courseGroupManager) {
        this.courseGroupManager = courseGroupManager;
    }
}
