package com.university.data_access.extension.account;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.university.model.account.lcp.AcademicDegreeCourse;

import java.sql.SQLException;


public class AcademicDegreeCourseHandler implements TypeHandlerCallback {
    @Override
    public void setParameter(ParameterSetter parameterSetter, Object o) throws SQLException {
        parameterSetter.setInt(((AcademicDegreeCourse) o).getId());
    }

    @Override
    public Object getResult(ResultGetter resultGetter) throws SQLException {
        int id = resultGetter.getInt();
        return resultGetter.wasNull() ? null : AcademicDegreeCourse.valueOf(id);
    }

    @Override
    public Object valueOf(String s) {
        return Enum.valueOf(AcademicDegreeCourse.class, s);
    }
}
