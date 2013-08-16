package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IReportDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Report;
import com.university.model.department.lcp.ReportType;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 22.07.13
 * Time: 2:04
 * To change this template use File | Settings | File Templates.
 */
public class ReportDaoImpl implements IReportDao {


    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void addReport(Report report) throws DatabaseException {
        try {
            Long id = (Long) sqlMapClient.insert("nsReport.addReport", report);
            report.setReportId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public void addReportInfo(Report report) throws DatabaseException {
        try {
            Long id = (Long) sqlMapClient.insert("nsReport.addReportInfo", report);
            report.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Report getReportById(int id) throws DatabaseException {
        try {
            return (Report) sqlMapClient.queryForObject("nsReport.getReportById", id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Report getReportByTitle(String title) throws DatabaseException {
        try {
            return (Report) sqlMapClient.queryForObject("nsReport.getReportByTitle", title);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Report> getReportsByType(ReportType reportType, int start, int end) throws DatabaseException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("reportType", reportType);
            params.put("start", start);
            params.put("end", end);

            return sqlMapClient.queryForList("nsReport.getReportsByType", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Report> getReportPagination(int start, int end) throws DatabaseException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();
            params.put("start", start);
            params.put("end", end);

            return sqlMapClient.queryForList("nsReport.getReportsByType", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void editReport(Report report) throws DatabaseException, EntityNotFoundException {
        try {

            int count = sqlMapClient.update("nsReport.editReport", report);
            if(count == 0){
                throw new EntityNotFoundException();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void removeReport(int reportId) throws DatabaseException, EntityNotFoundException {
        try {

            int count = sqlMapClient.update("nsReport.removeReport", reportId);
            if(count == 0){
                throw new EntityNotFoundException();
            }

        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
