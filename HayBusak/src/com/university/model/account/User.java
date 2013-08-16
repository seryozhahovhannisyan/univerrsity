package com.university.model.account;


import com.university.model.account.lcp.Profile;
import com.university.model.department.Faculty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User implements Serializable {

    /**
     * id               int(6) unsigned  (NULL)     NO              (NULL)           select,insert,update,references
     * name             varchar(50)      utf8_bin   NO              (NULL)           select,insert,update,references
     * surname          varchar(50)      utf8_bin   NO              (NULL)           select,insert,update,references
     * second_name      varchar(50)      utf8_bin   NO              (NULL)           select,insert,update,references
     * photo_path       varchar(256)     utf8_bin   YES             (NULL)           select,insert,update,references
     * dob              date             (NULL)     NO              (NULL)           select,insert,update,references
     * email            varchar(50)      utf8_bin   NO              (NULL)           select,insert,update,references
     * password         varchar(256)     utf8_bin   NO              (NULL)           select,insert,update,references
     * address          varchar(256)     utf8_bin   YES             (NULL)           select,insert,update,references
     * phone            varchar(50)      utf8_bin   YES             (NULL)           select,insert,update,references
     * is_active        int(1) unsigned  (NULL)     NO              (NULL)           select,insert,update,references
     * is_logged_on      int(1) unsigned  (NULL)     NO              (NULL)          select,insert,update,references
     * entrance_year    int(4) unsigned  (NULL)     YES             (NULL)           select,insert,update,references
     * profile_id       int(1) unsigned  (NULL)     YES     MUL     (NULL)           select,insert,update,references
     * course_group_id  int(5) unsigned  (NULL)     YES     MUL     (NULL)           select,insert,update,references
     * faculty_id       int(5) unsigned  (NULL)     YES     MUL     (NULL)           select,insert,update,references
     */
    private int id;
    private String name;
    private String surname;
    private String secondName;

    private String photoPath;
    private Date dob;
    private String email;
    private String password;
    private String address;
    private String phone;
    private boolean isActive;
    private boolean isLoggedOn;
    private int entranceYear;

    private Profile profile;

    private CourseGroup courseGroup;
    private Faculty faculty;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isLoggedOn() {
        return isLoggedOn;
    }

    public void setLoggedOn(boolean loggedOn) {
        isLoggedOn = loggedOn;
    }

    public int getEntranceYear() {
        return entranceYear;
    }

    public void setEntranceYear(int entranceYear) {
        this.entranceYear = entranceYear;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public CourseGroup getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(CourseGroup courseGroup) {
        this.courseGroup = courseGroup;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", secondName='" + secondName + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", isActive=" + isActive +
                ", isLoggedOn=" + isLoggedOn +
                ", entranceYear=" + entranceYear +
                ", profile=" + profile +
                ", courseGroup=" + courseGroup +
                ", faculty=" + faculty +
                '}';
    }
}