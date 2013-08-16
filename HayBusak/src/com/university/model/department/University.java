package com.university.model.department;

import com.university.model.general.lcp.Language;

import java.util.List;

public class University {

    private int id;
    private String phone;
    private String email;
    private String logoPath;
    private String link;

    private String name;
    private String abbreviation;
    private String address;
    private String info;

    private Language language;
    private List<UniversityInfo> universityInfos;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public List<UniversityInfo> getUniversityInfos() {
        return universityInfos;
    }

    public void setUniversityInfos(List<UniversityInfo> universityInfos) {
        this.universityInfos = universityInfos;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", logoPath='" + logoPath + '\'' +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", address='" + address + '\'' +
                ", info='" + info + '\'' +
                ", language=" + language +
                ", universityInfos=" + universityInfos +
                '}';
    }
}