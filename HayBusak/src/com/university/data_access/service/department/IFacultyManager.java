package com.university.data_access.service.department;


import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IFacultyManager {

    /*Create*/
    public void add(Faculty faculty, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException;
    /*Read*/
    public Faculty getById(int id) throws InternalErrorException;
    public List<Faculty> getAll() throws InternalErrorException;
    public List<Faculty> getByParams(Map<String, Object> params) throws InternalErrorException;

    /*Edit*/
    public void edit(Faculty faculty, File file, String path, String fileName) throws InternalErrorException, EntityNotFoundException;
    public void editInfo(FacultyInfo info) throws InternalErrorException, EntityNotFoundException;

    /*Delete*/
    public void remove(int id, String dataPath) throws InternalErrorException, EntityNotFoundException;
}
