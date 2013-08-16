package com.university.model.department;

import com.university.model.department.lcp.DocumentType;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 23.07.13
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class Document {

    private int id;
    private DocumentType documentType;
    private String serialNumber;
    private Date confirmDate;
    private String path;

    private String about;
    private List<DocumentInfo> documentInfos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public void setDocumentType(DocumentType documentType) {
        this.documentType = documentType;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Date getConfirmDate() {
        return confirmDate;
    }

    public void setConfirmDate(Date confirmDate) {
        this.confirmDate = confirmDate;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<DocumentInfo> getDocumentInfos() {
        return documentInfos;
    }

    public void setDocumentInfos(List<DocumentInfo> documentInfos) {
        this.documentInfos = documentInfos;
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", documentType=" + documentType +
                ", serialNumber='" + serialNumber + '\'' +
                ", confirmDate=" + confirmDate +
                ", about='" + about + '\'' +
                ", path='" + path + '\'' +
                ", documentInfos=" + documentInfos +
                '}';
    }
}
