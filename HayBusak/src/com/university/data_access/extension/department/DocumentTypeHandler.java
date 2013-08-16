package com.university.data_access.extension.department;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.university.model.department.lcp.DocumentType;

import java.sql.SQLException;


public class DocumentTypeHandler implements TypeHandlerCallback {

    @Override
    public void setParameter(ParameterSetter parameterSetter, Object o) throws SQLException {
        //int val = ((DocumentType) o).getId() * 10 + 1;
        int val = ((DocumentType) o).getId() ;
        parameterSetter.setInt(val);
    }

    @Override
    public Object getResult(ResultGetter resultGetter) throws SQLException {
        int id = resultGetter.getInt();
        id = id % 10;
        return resultGetter.wasNull() ? null : DocumentType.valueOf(id);
    }

    @Override
    public Object valueOf(String s) {
        return Enum.valueOf(DocumentType.class, s);
    }
}
