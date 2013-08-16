package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IFacultyDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FacultyDaoImpl implements IFacultyDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(Faculty faculty) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsFaculty.add", faculty);
            faculty.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addInfos(List<FacultyInfo> facultyInfos) throws DatabaseException {

        Map<String, List<FacultyInfo>> infos = new HashMap<String, List<FacultyInfo>>();
        infos.put("infos",facultyInfos);

        try {
            sqlMapClient.insert("nsFaculty.addInfos", infos);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Faculty getById(int id) throws DatabaseException {
        try {
            //Map<String,Object> params = new HashMap<String, Object>();
            //params.put("id",id);
            return (Faculty)sqlMapClient.queryForObject("nsFaculty.getById",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Faculty> getAll() throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsFaculty.getAll");
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Faculty> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsFaculty.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void edit(Faculty document) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsFaculty.edit",document);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void editInfo(FacultyInfo info) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsFaculty.editInfo",info);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void remove(int id) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.delete("nsFaculty.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }


}