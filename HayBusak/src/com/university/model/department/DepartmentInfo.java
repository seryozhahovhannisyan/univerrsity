package com.university.model.department;

import com.university.model.general.lcp.Language;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 23:20
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentInfo {

    private int departmentId;
    private String name;

    private Language language;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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
        return "DepartmentInfo{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", language=" + language +
                '}';
    }
}