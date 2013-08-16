package com.university.data_access.service.department.impl;

import com.university.data_access.dao.department.IDepartmentDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IDepartmentManager;
import com.university.model.department.Department;
import com.university.model.department.DepartmentInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 25.07.13
 * Time: 18:55
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class DepartmentManagerImpl implements IDepartmentManager {

    private IDepartmentDao departmentDao;

    public void setDepartmentDao(IDepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(Department department) throws InternalErrorException, DuplicateDataException {
        try {

            /*Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", department.getName());//todo letter
            List<Department> departments = departmentDao.getByParams(params);
            if (departments == null) {
                throw new InternalErrorException();
            }*/

            departmentDao.add(department);

            for(DepartmentInfo info : department.getDepartmentInfos()){
                info.setDepartmentId(department.getId());
            }

            departmentDao.addInfos(department.getDepartmentInfos());
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Department getById(int id) throws InternalErrorException {
        try {
            return departmentDao.getById(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public Department getById(int id, int langId) throws InternalErrorException {
        try {
            return departmentDao.getById(id,langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Department> getAll(int langId) throws InternalErrorException {
        try {
            return departmentDao.getAll(langId);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public List<Department> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return departmentDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }


    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void editInfo(DepartmentInfo info) throws InternalErrorException, EntityNotFoundException {
        try {
            departmentDao.editInfo(info);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void remove(int id) throws InternalErrorException, EntityNotFoundException {
        try {
            departmentDao.remove(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }
}
