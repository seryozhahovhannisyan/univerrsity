package com.university.data_access.extension.department;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.university.model.department.lcp.AcademicDegree;

import java.sql.SQLException;


public class AcademicDegreeHandler implements TypeHandlerCallback {

    @Override
    public void setParameter(ParameterSetter parameterSetter, Object o) throws SQLException {
        //int val = ((AcademicDegree) o).getId() * 10 + 1;
        int val = ((AcademicDegree) o).getId();//* 10 + 1;
        parameterSetter.setInt(val);
    }

    @Override
    public Object getResult(ResultGetter resultGetter) throws SQLException {
        int id = resultGetter.getInt();
        id = id % 10;
        return resultGetter.wasNull() ? null : AcademicDegree.valueOf(id);
    }

    @Override
    public Object valueOf(String s) {
        return Enum.valueOf(AcademicDegree.class, s);
    }
}
