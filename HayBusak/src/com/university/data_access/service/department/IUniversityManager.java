package com.university.data_access.service.department;


import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface IUniversityManager {

    /*Create*/
    public void add(University university, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException;

    /*Read*/
    public University getById(int id) throws InternalErrorException;
    public University getById(int id, int langId) throws InternalErrorException;
    public List<University> getAll(int langId) throws InternalErrorException;
    public List<University> getAllNames(int langId) throws InternalErrorException;
    public List<University> getByParams(Map<String, Object> params) throws InternalErrorException;

    /*Edit*/
    public void edit(University university, File file, String path, String fileName) throws InternalErrorException, EntityNotFoundException;
    public void editInfo(UniversityInfo info) throws InternalErrorException, EntityNotFoundException;

    /*Delete*/
    public void remove(University university, String path) throws InternalErrorException, EntityNotFoundException;

}
