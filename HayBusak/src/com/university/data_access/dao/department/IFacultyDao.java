package com.university.data_access.dao.department;


import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;

import java.util.List;
import java.util.Map;

public interface IFacultyDao {
    public void add(Faculty faculty) throws DatabaseException;
    public void addInfos(List<FacultyInfo> infos) throws DatabaseException;
    /*Read*/
    public Faculty getById(int id) throws DatabaseException;
    public List<Faculty> getAll() throws DatabaseException;
    public List<Faculty> getByParams(Map<String, Object> params) throws DatabaseException;
    /*Edit*/
    public void edit(Faculty department) throws DatabaseException, EntityNotFoundException;
    public void editInfo(FacultyInfo info) throws DatabaseException, EntityNotFoundException;
    /*Delete*/
    public void remove(int id)  throws DatabaseException, EntityNotFoundException;
}
