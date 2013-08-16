package com.university.data_access.service.department.impl;

import com.university.data_access.dao.department.IReportDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IReportManager;
import com.university.data_access.util.DataStorage;
import com.university.model.department.Report;
import com.university.model.department.lcp.ReportType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 2:06
 * To change this template use File | Settings | File Templates.
 */

@Transactional(readOnly = true)
public class ReportManagerImpl implements IReportManager {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void addReport(Report report) throws InternalErrorException, DuplicateDataException {
        try {

            Report r = reportDao.getReportByTitle(report.getTitle());

            if (r != null) {
                throw new DuplicateDataException();
            }

            if (report.getReportId() == -1) {
                reportDao.addReport(report);
            }

            reportDao.addReportInfo(report);

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Report getReportById(int id) throws InternalErrorException {
        try {
            return reportDao.getReportById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Report> getReportsByType(ReportType reportType, int start, int end) throws InternalErrorException {
        try {
            return reportDao.getReportsByType(reportType, start, end);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Report> getReportPagination(int start, int end) throws InternalErrorException {
        try {
            return reportDao.getReportPagination(start, end);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void editReport(Report report) throws InternalErrorException, EntityNotFoundException {
        try {
            reportDao.editReport(report);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void removeReport(int reportId) throws InternalErrorException, EntityNotFoundException {
        try {
            reportDao.removeReport(reportId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    private IReportDao reportDao;
    private DataStorage dataStorage;

    public void setReportDao(IReportDao reportDao) {
        this.reportDao = reportDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
}
