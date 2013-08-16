package com.university.model.department;

import com.university.model.general.lcp.Language;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 1:31
 * To change this template use File | Settings | File Templates.
 */
public class Faculty {

    private int id;
    private String phone;
    private String email;
    private String logoPath;

    private String name;
    private String info;

    private Language language;
    private List<FacultyInfo> facultyInfos;
    private Department department;

    private List<FacultyDegree> facultyDegrees;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public List<FacultyInfo> getFacultyInfos() {
        return facultyInfos;
    }

    public void setFacultyInfos(List<FacultyInfo> facultyInfos) {
        this.facultyInfos = facultyInfos;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<FacultyDegree> getFacultyDegrees() {
        return facultyDegrees;
    }

    public void setFacultyDegrees(List<FacultyDegree> facultyDegrees) {
        this.facultyDegrees = facultyDegrees;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", language=" + language +
                ", facultyInfos=" + facultyInfos +
                ", department=" + department +
                ", facultyDegrees=" + facultyDegrees +
                '}';
    }
}