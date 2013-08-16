package com.university.data_access.service.department;


import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;

import java.util.List;
import java.util.Map;

public interface IDepartmentManager {

    /*Create*/
    public void add(Department department) throws InternalErrorException, DuplicateDataException;

    /*Read*/
    public Department getById(int id) throws InternalErrorException;
    public Department getById(int id, int langId) throws InternalErrorException;
    public List<Department> getAll(int langId) throws InternalErrorException;
    public List<Department> getByParams(Map<String, Object> params) throws InternalErrorException;

    /*Edit*/
    public void editInfo(DepartmentInfo info) throws InternalErrorException, EntityNotFoundException;

    /*Delete*/
    public void remove(int departmentId) throws InternalErrorException, EntityNotFoundException;

}
