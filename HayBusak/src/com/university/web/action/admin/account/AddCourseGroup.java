package com.university.web.action.admin.account;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.model.account.CourseGroup;
import com.university.model.account.lcp.AcademicDegreeCourse;
import com.university.model.department.Faculty;
import com.university.model.department.lcp.AcademicDegreeInfo;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.validation.SkipValidation;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 05.08.13
 * Time: 1:21
 * To change this template use File | Settings | File Templates.
 */
public class AddCourseGroup extends ShellAction {

    private static final Logger logger = Logger.getLogger(AddCourseGroup.class.getSimpleName());
    private ICourseGroupManager groupManager;

    private AcademicDegreeCourse course;
    private String name;
    private int facultyId;

    @SkipValidation
    public String preExecute() {
        return SUCCESS;
    }

    @Override
    public String execute() {

        Faculty faculty = new Faculty();
        faculty.setId(facultyId);

        CourseGroup group = new CourseGroup();
        group.setCourse(course);
        group.setName(name);
        group.setFaculty(faculty);

        try {
            groupManager.add(group);
            return SUCCESS;
        } catch (InternalErrorException e) {
            logger.error(String.format("unable to add Group data [%s]", e.getMessage()));
            return ERROR;
        } catch (DuplicateDataException e) {
            logger.error(String.format("unable to add Group data [%s]", e.getMessage()));
            storeInSession(ConstantGeneral.ERR_MESSAGE,getText("errors.invalid.data"));
            return INPUT;
        }


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

    public void setName(String name) {
        this.name = name;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public void setGroupManager(ICourseGroupManager groupManager) {
        this.groupManager = groupManager;
    }
}
