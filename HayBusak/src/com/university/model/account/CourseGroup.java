package com.university.model.account;

import com.university.model.department.Faculty;
import com.university.model.account.lcp.AcademicDegreeCourse;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
public class CourseGroup {

    private int id;
    private String name;
    private AcademicDegreeCourse course;
    private Faculty faculty;

    private List<User> groupUsers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AcademicDegreeCourse getCourse() {
        return course;
    }

    public void setCourse(AcademicDegreeCourse course) {
        this.course = course;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public List<User> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<User> groupUsers) {
        this.groupUsers = groupUsers;
    }

    @Override
    public String toString() {
        return "CourseGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", course=" + course +
                ", faculty=" + faculty +
                ", groupUsers=" + groupUsers +
                '}';
    }
}
