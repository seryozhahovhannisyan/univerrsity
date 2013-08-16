package com.university.data_access.extension;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;
import com.university.model.general.lcp.Language;

import java.sql.SQLException;


public class LanguageHandler implements TypeHandlerCallback {
    @Override
    public void setParameter(ParameterSetter parameterSetter, Object o) throws SQLException {
        parameterSetter.setInt(((Language) o).getValue());
    }

    @Override
    public Object getResult(ResultGetter resultGetter) throws SQLException {
        int id = resultGetter.getInt();
        return resultGetter.wasNull() ? null : Language.valueOf(id);
    }

    @Override
    public Object valueOf(String s) {
        return Enum.valueOf(Language.class, s);
    }
}
