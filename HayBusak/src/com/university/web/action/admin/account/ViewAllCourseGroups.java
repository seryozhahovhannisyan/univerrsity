package com.university.web.action.admin.account;

import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.data_access.service.account.IGroupSubjectManager;
import com.university.data_access.service.account.ISubjectManager;
import com.university.model.account.CourseGroup;
import com.university.model.account.GroupSubject;
import com.university.model.account.Subject;
import com.university.model.account.lcp.AcademicDegreeCourse;
import com.university.model.department.Faculty;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 05.08.13
 * Time: 1:21
 * To change this template use File | Settings | File Templates.
 */
public class ViewAllCourseGroups extends ShellAction {

    private static final Logger logger = Logger.getLogger(ViewAllCourseGroups.class.getSimpleName());
    private ICourseGroupManager groupManager;
    private ISubjectManager subjectManager;
    private IGroupSubjectManager groupSubjectManager;

    private List<CourseGroup> courseGroups;
    private List<Subject> subjects;
    private List<GroupSubject> groupSubjects;
    private AcademicDegreeCourse course;
    private int facultyId;
    private int act;

    @Override
    public String execute() {

        Map<String,Object> params = new HashMap<String, Object>();
        if(course != null){
            params.put("course",course);
        }
        if(CommonValidator.isPositiveNumber(""+facultyId)){
            params.put("facultyId",facultyId);
        }

        try {
            courseGroups = groupManager.getByParams(params);
            if(act == 1){
                subjects = subjectManager.getByParams(null);
                groupSubjects = groupSubjectManager.getByParams(null);
            }
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to load courseGroups data [%s]", e.getMessage()));
            return ERROR;
        }
    }

    public List<CourseGroup> getCourseGroups() {
        return courseGroups;
    }

    public AcademicDegreeCourse[] getCourses() {
        return AcademicDegreeCourse.values();
    }

    public List<AcademicDegreeInfo> getDegreeInfos() {
        Language language = (Language) getFromSession(ConstantGeneral.LANGUAGE);
        if (language == null) {
            return null;
        }
        return AcademicDegreeInfo.listValueOfLanguage(language);
    }

    public void setCourse(String courseId) {

        try {
            Integer key = Integer.parseInt(courseId);
            this.course = AcademicDegreeCourse.valueOf(key);
        } catch (NumberFormatException e) {
            logger.error(String.format("unable to cast data [%s],error is [%s]", courseId, e.getMessage()));
        }
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public int getAct() {
        return act;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public List<GroupSubject> getGroupSubjects() {
        return groupSubjects;
    }

    public void setAct(int act) {
        this.act = act;
    }


    public void setSubjectManager(ISubjectManager subjectManager) {
        this.subjectManager = subjectManager;
    }

    public void setGroupManager(ICourseGroupManager groupManager) {
        this.groupManager = groupManager;
    }

    public void setGroupSubjectManager(IGroupSubjectManager groupSubjectManager) {
        this.groupSubjectManager = groupSubjectManager;
    }
}
