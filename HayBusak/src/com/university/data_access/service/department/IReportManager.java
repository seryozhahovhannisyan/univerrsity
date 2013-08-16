package com.university.data_access.service.department;

import com.university.model.department.Report;
import com.university.model.department.lcp.ReportType;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;

import java.util.List;


public interface IReportManager {

    /*Create*/
    public void addReport(Report report) throws InternalErrorException, DuplicateDataException;

    /*Read*/
    public Report getReportById(int id) throws InternalErrorException;

    public List<Report> getReportsByType(ReportType reportType, int start, int end) throws InternalErrorException;

    public List<Report> getReportPagination(int start, int end) throws InternalErrorException;

    /*Edit*/
    public void editReport(Report report) throws InternalErrorException, EntityNotFoundException;

    /*Delete*/
    public void removeReport(int reportId) throws InternalErrorException, EntityNotFoundException;
}
