package com.university.data_access.service.department.impl;

import com.university.data_access.dao.department.IUniversityDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IUniversityManager;
import com.university.data_access.util.DataStorage;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;
import com.university.web.util.ConstantGeneral;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional(readOnly = true)
public class UniversityManagerImpl implements IUniversityManager {

    private IUniversityDao universityDao;
    private DataStorage dataStorage;

    public void setUniversityDao(IUniversityDao universityDao) {
        this.universityDao = universityDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(University university, File file, String path, String fileName) throws InternalErrorException, DuplicateDataException {
        try {

            Map<String, Object> params = new HashMap<String, Object>();


            /*List<University> departments = departmentDao.getByParams(params);
            if (departments != null) {
                throw new DuplicateDataException();
            }*/

            /*int count = departmentDao.getDepartmentInfoCountByName(universityInfo.getName());
            if (count != 0) {
                throw new DuplicateDataException();
            }*/

                dataStorage.setPath(path);
                String logoPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR + dataStorage.add(file, university.getPhone(), fileName);
                university.setLogoPath(logoPath);


            universityDao.add(university);

            List<UniversityInfo> universityInfos = university.getUniversityInfos();
            for (UniversityInfo info : universityInfos) {
                info.setUniversityId(university.getId());
            }

            universityDao.addInfos(university.getUniversityInfos());

        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public University getById(int id) throws InternalErrorException {
        try {
            return universityDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public University getById(int id, int langId) throws InternalErrorException {
        try {
            return universityDao.getById(id, langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<University> getAll(int langId) throws InternalErrorException {
        try {
            return universityDao.getAll(langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<University> getAllNames(int langId) throws InternalErrorException {
        try {
            return universityDao.getAllNames(langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<University> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return universityDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void edit(University university, File file, String path, String fileName) throws InternalErrorException, EntityNotFoundException {

        try {

            University u = universityDao.getById(university.getId());
            if (file != null) {
                dataStorage.setPath(path);
                dataStorage.remove(u.getPhone(), u.getLogoPath());
                String logoPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR + dataStorage.add(file, university.getPhone(), fileName);
                university.setLogoPath(logoPath);
            }


            universityDao.edit(university);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void editInfo(UniversityInfo info) throws InternalErrorException, EntityNotFoundException {
        try {
            universityDao.editInfo(info);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void remove(University university, String path) throws InternalErrorException, EntityNotFoundException {
        try {

            //University university = universityDao.getById(id);

            dataStorage.setPath(path);
            dataStorage.remove(university.getPhone(), university.getLogoPath());
            universityDao.remove(university.getId());
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }
}
