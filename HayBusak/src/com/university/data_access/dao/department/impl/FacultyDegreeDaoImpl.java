package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IFacultyDegreeDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.FacultyDegree;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 29.07.13
 * Time: 19:33
 * To change this template use File | Settings | File Templates.
 */
public class FacultyDegreeDaoImpl implements IFacultyDegreeDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(FacultyDegree facultyDegree) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsFacultyDegree.add", facultyDegree);
            facultyDegree.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public FacultyDegree getById(int id) throws DatabaseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FacultyDegree getById(int id, int langId) throws DatabaseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getAll(int langId) throws DatabaseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getAllNames(int langId) throws DatabaseException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsFacultyDegree.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void edit(FacultyDegree facultyDegree) throws DatabaseException, EntityNotFoundException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void remove(int id) throws DatabaseException, EntityNotFoundException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
