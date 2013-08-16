package com.university.data_access.extension.department;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.university.model.department.lcp.ReportType;

import java.sql.SQLException;


public class ReportTypeHandler implements TypeHandlerCallback {
    @Override
    public void setParameter(ParameterSetter parameterSetter, Object o) throws SQLException {
        parameterSetter.setInt(((ReportType) o).getId());
    }

    @Override
    public Object getResult(ResultGetter resultGetter) throws SQLException {
        int id = resultGetter.getInt();
        return resultGetter.wasNull() ? null : ReportType.valueOf(id);
    }

    @Override
    public Object valueOf(String s) {
        return Enum.valueOf(ReportType.class, s);
    }
}
