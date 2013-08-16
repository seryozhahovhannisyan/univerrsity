package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IUniversityDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class UniversityDaoImpl implements IUniversityDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(University university) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsUniversity.add", university);
            university.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addInfos(List<UniversityInfo> universityInfos) throws DatabaseException {

        Map<String, List<UniversityInfo>> infos = new HashMap<String, List<UniversityInfo>>();
        infos.put("infos", universityInfos);

        try {
            sqlMapClient.insert("nsUniversity.addInfos", infos);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public University getById(int id) throws DatabaseException {
        try {
            return (University)sqlMapClient.queryForObject("nsUniversity.getByIdAllLang",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public University getById(int id, int langId) throws DatabaseException {
        try {
            Map<String,Integer> params = new HashMap<String, Integer>();
            params.put("id",id);
            params.put("langId",langId);
            return (University)sqlMapClient.queryForObject("nsUniversity.getById",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<University> getAll(int langId) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsUniversity.getAll",langId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<University> getAllNames(int langId) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsUniversity.getAllNames",langId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<University> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsUniversity.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void edit(University university) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsUniversity.edit", university);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void editInfo(UniversityInfo info) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsUniversity.editInfo", info);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void remove(int departmentId) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.delete("nsUniversity.remove",departmentId);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}