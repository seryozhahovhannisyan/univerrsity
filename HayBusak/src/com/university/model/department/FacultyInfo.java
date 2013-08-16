package com.university.model.department;


import com.university.model.general.lcp.Language;

public class FacultyInfo {

    private int facultyId;

    private String name;
    private String info;

    private Language language;

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
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

    @Override
    public String toString() {
        return "FacultyInfo{" +
                "facultyId=" + facultyId +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", language=" + language +
                '}';
    }
}