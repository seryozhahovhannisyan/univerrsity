package com.university.data_access.service.department;

import com.university.model.department.FacultyDegree;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 29.07.13
 * Time: 4:58
 * To change this template use File | Settings | File Templates.
 */
public interface IFacultyDegreeManager {
    /*Create*/

    /**
     * @param facultyDegrees
     * @param files
     * @param path
     * @param packageNames   depends the university , that will get from session stored universities
     * @param fileNames
     * @throws InternalErrorException
     * @throws DuplicateDataException
     */
    //   public void add(FacultyDegree facultyDegree, File file, String path,String packageName,  String fileName) throws InternalErrorException, DuplicateDataException;
    public void add(List<FacultyDegree> facultyDegrees, List<File> files, String path, List<String> packageNames, List<String> fileNames) throws InternalErrorException, DuplicateDataException;

    /*Read*/
    public FacultyDegree getById(int id) throws InternalErrorException;

    public FacultyDegree getById(int id, int langId) throws InternalErrorException;

    public List<FacultyDegree> getAll(int langId) throws InternalErrorException;

    public List<FacultyDegree> getAllNames(int langId) throws InternalErrorException;

    public List<FacultyDegree> getByParams(Map<String, Object> params) throws InternalErrorException;

    /*Edit*/
    public void edit(FacultyDegree facultyDegree, File file, String path, String packageName, String fileName) throws InternalErrorException, EntityNotFoundException;

    /*Delete*/
    public void remove(int id, String packageName, String path) throws InternalErrorException, EntityNotFoundException;
}
