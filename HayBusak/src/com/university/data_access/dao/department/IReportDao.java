package com.university.data_access.dao.department;


import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Report;
import com.university.model.department.lcp.ReportType;

import java.util.List;

public interface IReportDao {

    /*Create*/
    public void addReport(Report report) throws DatabaseException;
    public void addReportInfo(Report report) throws DatabaseException;

    /*Read*/
    public Report getReportById(int id) throws DatabaseException;
    public Report getReportByTitle(String title) throws DatabaseException;

    public List<Report> getReportsByType(ReportType reportType, int start, int end) throws DatabaseException;

    public List<Report> getReportPagination(int start, int end) throws DatabaseException;

    /*Edit*/
    public void editReport(Report report) throws DatabaseException, EntityNotFoundException;

    /*Delete*/
    public void removeReport(int reportId) throws DatabaseException, EntityNotFoundException;
}
