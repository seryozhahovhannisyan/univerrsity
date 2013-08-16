package service;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.account.CourseGroup;
import com.university.model.account.User;
import com.university.model.account.lcp.Profile;
import com.university.model.department.Faculty;
import com.university.model.general.lcp.Language;
import general.ShellTest;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 09.08.13
 * Time: 17:57
 * To change this template use File | Settings | File Templates.
 */
public class UserManagerTest extends ShellTest {

    @Test
    public void add() {

        for (int i = 1; i < 1500; i++) {

            Faculty faculty = new Faculty();
            faculty.setId(4910);
            Faculty faculty2 = new Faculty();
            faculty2.setId(4907);
            CourseGroup group1 = new CourseGroup();
            group1.setId(1);

            CourseGroup group2 = new CourseGroup();
            group2.setId(2);

            User user = new User();

            user.setActive(i % 2 == 0);
            user.setAddress("" + i);
            user.setCourseGroup(i % 2 == 0 ? group1 : group2);
            user.setDob(new Date());
            user.setEmail("setEmail" + i + "@gmail.com");

            user.setFaculty(i % 2 == 0 ? faculty : faculty2);
            user.setEntranceYear(2010 + (i / 100));
            user.setLoggedOn(i % 2 == 0);
            user.setName("setName " + i);
            user.setPassword("setPassword " + i);
            user.setPhone("123456" + i);
            user.setProfile(Profile.valueOf(i % 4 == 0 ? 4 : i % 4));
            //user.setProfile(Profile.STUDENT);
            user.setSecondName("setSecondName " + i);
            user.setSurname("setSurname " + i);

            try {
                userManager.add(user, null, null, null);
            } catch (DuplicateDataException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            } catch (InternalErrorException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

    }

    @Test
    public void login() {
        try {
            User user = userManager.login("setEmail2@gmail.com", "setPassword 2", Language.ARMENIAN.getValue());
            System.out.println(user);
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (EntityNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getById() {
        try {
            User user = userManager.getUserById(1, Language.ARMENIAN.getValue());
            System.out.println(user);
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (EntityNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void getAll() {
        try {
            List<User> users = userManager.getAll(Language.ARMENIAN.getValue());
            System.out.println(users.size());
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


    @Test
    public void getByParams() {

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("isActive", true);
        params.put("isLoggedOn", true);
        params.put("entranceYear", 1);
        params.put("profileId", 1);
        params.put("courseGroupId", 2);
        params.put("langId", Language.ARMENIAN.getValue());
        //params.put("facultyId", 4907);

        try {
            List<User> users = userManager.getByParams(params);
            System.out.println(users.size());
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void update() {
    }

    @Test
    public void deleteById() {
    }
}
