package com.university.data_access.service.department.impl;

import com.university.data_access.dao.department.IFacultyDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyManager;
import com.university.data_access.util.DataStorage;
import com.university.model.department.Faculty;
import com.university.model.department.FacultyInfo;
import com.university.web.util.ConstantGeneral;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public class FacultyManagerImpl implements IFacultyManager {

    private IFacultyDao facultyDao;
    private DataStorage dataStorage;

    public void setFacultyDao(IFacultyDao facultyDao) {
        this.facultyDao = facultyDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Faculty faculty, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();


            /*List<University> departments = departmentDao.getByParams(params);
            if (departments != null) {
                throw new DuplicateDataException();
            }*/

            /*int count = departmentDao.getDepartmentInfoCountByName(departmentInfo.getName());
            if (count != 0) {
                throw new DuplicateDataException();
            }*/

            dataStorage.setPath(path);
            String pckg = faculty.getDepartment().getUniversity().getPhone() + ConstantGeneral.FILE_SEPARATOR +
                    ConstantGeneral.PACKAGE_FACULTY + ConstantGeneral.FILE_SEPARATOR +
                    faculty.getDepartment().getId() ;
            String logoPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR +
                    dataStorage.add(file, pckg, fileName);
            faculty.setLogoPath(logoPath);

            facultyDao.add(faculty);

            List<FacultyInfo> documentInfos = faculty.getFacultyInfos();
            for (FacultyInfo info : documentInfos) {
                info.setFacultyId(faculty.getId());
            }

            facultyDao.addInfos(faculty.getFacultyInfos());

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Faculty getById(int id) throws InternalErrorException {
        try {
            return facultyDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Faculty> getAll() throws InternalErrorException {
        try {
            return facultyDao.getAll();
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Faculty> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return facultyDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void edit(Faculty faculty, File file, String path, String fileName) throws InternalErrorException, EntityNotFoundException {

        try {

            Faculty f = facultyDao.getById(faculty.getId());

            if (file != null) {

                dataStorage.setPath(path);

                String pckg = faculty.getDepartment().getUniversity().getPhone() + ConstantGeneral.FILE_SEPARATOR +
                        ConstantGeneral.PACKAGE_FACULTY + ConstantGeneral.FILE_SEPARATOR +
                        faculty.getDepartment().getId() + ConstantGeneral.FILE_SEPARATOR;

                dataStorage.remove(pckg,fileName);

                String logoPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR +
                        dataStorage.add(file, pckg, fileName);
                faculty.setLogoPath(logoPath);
            }
            facultyDao.edit(faculty);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void editInfo(FacultyInfo info) throws InternalErrorException, EntityNotFoundException {
        try {
            facultyDao.editInfo(info);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void remove(int id, String dataPath) throws InternalErrorException, EntityNotFoundException {
        try {//todo improve
            facultyDao.remove(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
