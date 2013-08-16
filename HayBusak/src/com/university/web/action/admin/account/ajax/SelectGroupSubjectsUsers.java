package com.university.web.action.admin.account.ajax;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.data_access.service.account.IUserManager;
import com.university.model.account.CourseGroup;
import com.university.model.account.GroupSubject;
import com.university.model.account.User;
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
public class SelectGroupSubjectsUsers extends ShellAction {
    //for ajax
    private int groupId;
    private List<GroupSubject> groupSubjects;
    private List<User> users;

    @Override
    public String execute() throws Exception {

        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("langId", language.getValue());
        params.put("groupId", groupId);
        params.put("courseGroupId", groupId);
        params.put("isActive", true);
        //params.put("entranceYear", 1);
        //params.put("profileId", Profile.STUDENT.getId());
        try {
            groupSubjects = groupSubjectManager.getByParams(params);
            users = userManager.getByParams(params);
            responseDto.setStatus(ResponseStatus.SUCCESS);
        } catch (InternalErrorException e) {
            responseDto.setStatus(ResponseStatus.INTERNAL_ERROR);
            responseDto.addMessage(getText("errors.internal.server"));
        }
        return SUCCESS;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<GroupSubject> getGroupSubjects() {
        return groupSubjects;
    }

    public List<User> getUsers() {
        return users;
    }

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    private ResponseDto responseDto;
    private IGroupSubjectManager groupSubjectManager;
    private IUserManager userManager;

    public void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public void setGroupSubjectManager(IGroupSubjectManager groupSubjectManager) {
        this.groupSubjectManager = groupSubjectManager;
    }

    public void setUserManager(IUserManager userManager) {
        this.userManager = userManager;
    }
}
