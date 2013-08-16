package com.university.data_access.dao.department.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.university.data_access.dao.department.IDocumentDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Document;
import com.university.model.department.DocumentInfo;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DocumentDaoImpl implements IDocumentDao {

    private SqlMapClient sqlMapClient;

    public void setSqlMapClient(SqlMapClient sqlMapClient) {
        this.sqlMapClient = sqlMapClient;
    }

    @Override
    public void add(Document document) throws DatabaseException {
        try {
            Integer id = (Integer) sqlMapClient.insert("nsDocument.add", document);
            document.setId(id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void addInfos(List<DocumentInfo> documentInfos) throws DatabaseException {

        Map<String, List<DocumentInfo>> infos = new HashMap<String, List<DocumentInfo>>();
        infos.put("infos",documentInfos);

        try {
            sqlMapClient.insert("nsDocument.addInfos", infos);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public Document getById(int id) throws DatabaseException {
        try {
            return (Document)sqlMapClient.queryForObject("nsDocument.getById",id);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Document> getAll() throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsDocument.getAll");
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public List<Document> getByParams(Map<String, Object> params) throws DatabaseException {
        try {
            return sqlMapClient.queryForList("nsDocument.getByParams",params);
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void edit(Document document) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsDocument.edit",document);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void editInfo(DocumentInfo info) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.update("nsDocument.editInfo",info);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }

    @Override
    public void remove(int id) throws DatabaseException, EntityNotFoundException {
        try {
            int count = sqlMapClient.delete("nsDocument.remove",id);
            if(count == 0){
                throw new EntityNotFoundException();
            }
        } catch (SQLException e) {
            throw new DatabaseException(e);
        }
    }
}