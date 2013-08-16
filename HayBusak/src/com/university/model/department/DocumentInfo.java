package com.university.model.department;

import com.university.model.general.lcp.Language;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class DocumentInfo {

    private String about;
    private int documentId;
    private Language language;

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    @Override
    public String toString() {
        return "DocumentInfo{" +
                "about='" + about + '\'' +
                ", documentId=" + documentId +
                ", language=" + language +
                '}';
    }
}