package com.university.data_access.dao.department;


import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;

import java.util.List;
import java.util.Map;

public interface IUniversityDao {


    public void add(University university) throws DatabaseException;
    public void addInfos(List<UniversityInfo> universityInfos) throws DatabaseException;
    /*Read*/
    public University getById(int id) throws DatabaseException;
    public University getById(int id, int langId) throws DatabaseException;
    public List<University> getAll(int langId) throws DatabaseException;
    public List<University> getAllNames(int langId) throws DatabaseException;
    public List<University> getByParams(Map<String, Object> params) throws DatabaseException;

    /*Edit*/
    public void edit(University university) throws DatabaseException, EntityNotFoundException;
    public void editInfo(UniversityInfo info) throws DatabaseException, EntityNotFoundException;

    /*Delete*/
    public void remove(int departmentId)  throws DatabaseException, EntityNotFoundException;
}
