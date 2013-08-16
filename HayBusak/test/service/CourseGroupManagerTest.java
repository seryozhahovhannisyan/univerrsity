package service;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.CourseGroup;
import com.university.model.account.lcp.AcademicDegreeCourse;
import com.university.model.department.Faculty;
import com.university.web.util.CommonValidator;
import general.ShellTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 06.08.13
 * Time: 0:30
 * To change this template use File | Settings | File Templates.
 */
public class CourseGroupManagerTest extends ShellTest {

    @Test
    public void addGroups() {
        for (int i = 1; i < 150; i++) {

            CourseGroup group = new CourseGroup();
            Faculty faculty = new Faculty();
            faculty.setId(4908);

            Faculty faculty2 = new Faculty();
            faculty2.setId(4909);

            group.setFaculty(i % 2 == 0 ? faculty : faculty2);

            int j = i % 9;
            j = j == 0 ? 9 : (j);

            group.setCourse(AcademicDegreeCourse.valueOf(j));
            group.setName("name is " + (i + 150));

            try {
                courseGroupManager.add(group);
            } catch (InternalErrorException e) {
                e.printStackTrace();
            } catch (DuplicateDataException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void addSubjects() {

        List<Integer> groupsId = new ArrayList<Integer>();
        List<Integer> subjectsId = new ArrayList<Integer>();

        for (int i = 1; i < 100; i++) {
            groupsId.add(i);
            if(i<=9){
                subjectsId.add(i);
            }
        }


        try {
            courseGroupManager.addSubjects(groupsId, subjectsId);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        } catch (DuplicateDataException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getByParams() {
        Map<String, Object> params = new HashMap<String, Object>();
        //params.put("course", AcademicDegreeCourse.COLLEGE_XI);
        params.put("facultyId", 4910);

        try {
            List<CourseGroup> groups = courseGroupManager.getByParams(params);
            System.out.println(groups);
        } catch (InternalErrorException e) {
            e.printStackTrace();
        }
    }
}
