package com.university.model.department;

import com.university.model.general.lcp.Language;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 23:19
 * To change this template use File | Settings | File Templates.
 */
public class UniversityInfo {

    private int universityId;

    private String name;
    private String abbreviation;
    private String address;
    private String info;
    private Language language;

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        return "UniversityInfo{" +
                "universityId=" + universityId +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", address='" + address + '\'' +
                ", info='" + info + '\'' +
                ", language=" + language +
                '}';
    }
}