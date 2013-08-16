package com.university.data_access.dao.account.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.account.ICourseGroupDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.account.CourseGroup;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 19:54
 * To change this template use File | Settings | File Templates.
 */
public class CourseGroupDaoImpl implements ICourseGroupDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(CourseGroup group) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsCourseGroup.add", group);
            group.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public void addSubjects(List<Integer> groupsId, List<Integer> subjectsId) throws DatabaseException {

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("groups",groupsId);
        params.put("subjects",subjectsId);
        try {
            sqlMapClient.insert("nsCourseGroup.addSubjects", params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }

    }

    @Override
    public CourseGroup getById(int id) throws DatabaseException {
        try {
            return (CourseGroup)sqlMapClient.queryForObject("nsCourseGroup.getById", id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<CourseGroup> getAll() throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsCourseGroup.getAll");
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<CourseGroup> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsCourseGroup.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void update(CourseGroup group) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsCourseGroup.update",group);
            if(count == 0){
               throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void delete(int id) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.delete("nsCourseGroup.update",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}
