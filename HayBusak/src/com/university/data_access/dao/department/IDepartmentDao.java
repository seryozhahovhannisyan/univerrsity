package com.university.data_access.dao.department;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;

import java.util.List;
import java.util.Map;

public interface IDepartmentDao {


    public void add(Department department) throws DatabaseException;
    public void addInfos(List<DepartmentInfo> infos) throws DatabaseException;
    /*Read*/
    public Department getById(int id) throws DatabaseException;
    public Department getById(int id, int langId) throws DatabaseException;
    public List<Department> getAll(int langId) throws DatabaseException;
    public List<Department> getByParams(Map<String, Object> params) throws DatabaseException;

    /*Edit*/
    public void editInfo(DepartmentInfo info) throws DatabaseException, EntityNotFoundException;

    /*Delete*/
    public void remove(int id)  throws DatabaseException, EntityNotFoundException;
}
