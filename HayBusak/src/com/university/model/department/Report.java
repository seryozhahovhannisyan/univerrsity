package com.university.model.department;

import com.university.model.department.lcp.ReportType;
import com.university.model.general.lcp.Language;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 1:31
 * To change this template use File | Settings | File Templates.
 */
public class Report {

    private long id;
    private long reportId=-1;
    private String title;
    private Date creationDate;
    private String content;
    private String iconPath;
    private Language language;
    private int departmentId;
    private ReportType type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getReportId() {
        return reportId;
    }

    public void setReportId(long reportId) {
        this.reportId = reportId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public ReportType getType() {
        return type;
    }

    public void setType(ReportType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", creationDate=" + creationDate +
                ", iconPath='" + iconPath + '\'' +
                ", language=" + language +
                ", departmentId=" + departmentId +
                ", type=" + type +
                '}';
    }
}
