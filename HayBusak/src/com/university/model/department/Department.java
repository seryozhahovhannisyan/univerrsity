package com.university.model.department;

import com.university.model.general.lcp.Language;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class Department {

    private int id;

    private String name;
    private Language language;

    private University university;
    private List<DepartmentInfo> departmentInfos;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    public List<DepartmentInfo> getDepartmentInfos() {
        return departmentInfos;
    }

    public void setDepartmentInfos(List<DepartmentInfo> departmentInfos) {
        this.departmentInfos = departmentInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", university=" + university +
                ", departmentInfos=" + departmentInfos +
                ", name='" + name + '\'' +
                ", language=" + language +
                '}';
    }
}