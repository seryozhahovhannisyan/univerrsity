package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IDepartmentDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 25.07.13
 * Time: 19:27
 * To change this template use File | Settings | File Templates.
 */
public class DepartmentDaoImpl implements IDepartmentDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(Department department) throws DatabaseException {
        try {
            Integer id = (Integer)sqlMapClient.insert("nsDepartment.add",department);
            department.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addInfos(List<DepartmentInfo> infos) throws DatabaseException {
        try {
            Map<String,List<DepartmentInfo>> params =new HashMap<String, List<DepartmentInfo>>();
            params.put("infos",infos);


            sqlMapClient.insert("nsDepartment.addInfos",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Department getById(int id) throws DatabaseException {
        try {
            return (Department)sqlMapClient.queryForObject("nsDepartment.getByIdAllLang",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Department getById(int id, int langId) throws DatabaseException {
        try {
            Map<String,Integer> params = new HashMap<String, Integer>();
            params.put("id",id);
            params.put("langId",langId);
            return (Department)sqlMapClient.queryForObject("nsDepartment.getById",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Department> getAll(int langId) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsDepartment.getAll",langId);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Department> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsDepartment.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }


    @Override
    public void editInfo(DepartmentInfo info) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsDepartment.editInfo",info);
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
            int count = sqlMapClient.update("nsDepartment.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
