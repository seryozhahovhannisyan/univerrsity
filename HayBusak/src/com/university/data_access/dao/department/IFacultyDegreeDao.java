package com.university.data_access.dao.department;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.FacultyDegree;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 29.07.13
 * Time: 5:08
 * To change this template use File | Settings | File Templates.
 */
public interface IFacultyDegreeDao {
    public void add(FacultyDegree facultyDegree) throws DatabaseException;
    /*Read*/
    public FacultyDegree getById(int id) throws DatabaseException;
    public FacultyDegree getById(int id, int langId) throws DatabaseException;
    public List<FacultyDegree> getAll(int langId) throws DatabaseException;
    public List<FacultyDegree> getAllNames(int langId) throws DatabaseException;
    public List<FacultyDegree> getByParams(Map<String, Object> params) throws DatabaseException;
    /*Edit*/
    public void edit(FacultyDegree facultyDegree) throws DatabaseException, EntityNotFoundException;
    /*Delete*/
    public void remove(int id)  throws DatabaseException, EntityNotFoundException;
}
