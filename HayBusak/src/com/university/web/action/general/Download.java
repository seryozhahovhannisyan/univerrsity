package com.university.web.action.general;

import com.university.data_access.util.DataStorage;
import com.university.web.util.CommonValidator;
import com.university.web.util.ConstantGeneral;
import com.university.web.util.ShellAction;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.InputStream;
import java.lang.String;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 13:44
 * To change this template use File | Settings | File Templates.
 */
public class Download extends ShellAction {

    private static final Logger logger = Logger.getLogger(Download.class.getSimpleName());
    private DataStorage dataStorage;
    private InputStream result;
    private String filePath;

    @Override
    public String execute() {
        try {

            filePath = filePath.replaceFirst(ConstantGeneral.MAIN_PATH,"");
            //dataStorage.setPath(ServletActionContext.getServletContext().getContextPath());
            dataStorage.setPath(DATA_FILE_PATH);
            File file = dataStorage.getFile(filePath);
            result = FileUtils.openInputStream(file);

            filePath = file.getName();
            storeInSession(ConstantGeneral.INFO, "The book is downloaded");
            return SUCCESS;
        } catch (Exception e) {
            logger.error(String.format("unable to get all Download data [%s]", e));
            storeInSession(ConstantGeneral.ERR_MESSAGE, e.getMessage());
            return ERROR;
        }
    }

    @Override
    public void validate() {
        if (CommonValidator.isEmpty(filePath)) {
            logger.error(String.format("unable to get all Download data as filePath is invalid"));
            storeInSession(ConstantGeneral.ERR_MESSAGE, getText("errors.number"));
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public InputStream getResult() {
        return result;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
}
