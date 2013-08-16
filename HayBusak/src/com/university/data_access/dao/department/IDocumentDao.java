package com.university.data_access.dao.department;

import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.model.department.Document;
import com.university.model.department.DocumentInfo;

import java.util.List;
import java.util.Map;

public interface IDocumentDao {


    public void add(Document document) throws DatabaseException;
    public void addInfos(List<DocumentInfo> documentInfos) throws DatabaseException;
    /*Read*/
    public Document getById(int id) throws DatabaseException;
    public List<Document> getAll() throws DatabaseException;
    public List<Document> getByParams(Map<String, Object> params) throws DatabaseException;

    /*Edit*/
    public void edit(Document document) throws DatabaseException, EntityNotFoundException;
    public void editInfo(DocumentInfo info) throws DatabaseException, EntityNotFoundException;

    /*Delete*/
    public void remove(int id)  throws DatabaseException, EntityNotFoundException;
}
