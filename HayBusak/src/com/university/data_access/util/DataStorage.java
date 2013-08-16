package com.university.data_access.util;

import com.university.web.util.ConstantGeneral;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class DataStorage {

    private String path;

    private File storage;

    public String add(File file, String mainPackageName, String fileName) throws IOException {
        mainPackageName.trim();
        mainPackageName = mainPackageName.replace(" ", "_");
        mainPackageName = mainPackageName.replace("@", "_");
        mainPackageName = mainPackageName.replace(".", "_");

        String filePath = storage.getAbsolutePath() + ConstantGeneral.FILE_SEPARATOR + mainPackageName + ConstantGeneral.FILE_SEPARATOR + fileName;

        File newFile = new File(filePath);
        FileUtils.copyFile(file, newFile);
        /*int rootPathIndex = path.lastIndexOf(ConstantGeneral.FILE_SEPARATOR);
        String rootPath = path.substring(rootPathIndex);*/
        return mainPackageName + ConstantGeneral.FILE_SEPARATOR + fileName;
    }

    public File getFile(String name) throws FileNotFoundException {
        File file = new File(storage.getAbsolutePath() + ConstantGeneral.FILE_SEPARATOR + name);
        //File file = new File(name);
        if (file.exists()) {
            return file;
        }
        throw new FileNotFoundException(name);
    }

    public void remove(String mainPackageName, String name) throws IOException {

        mainPackageName = mainPackageName.replace(" ", "_");
        mainPackageName = mainPackageName.replace("@", "_");
        mainPackageName = mainPackageName.replace(".", "_");

        int index = name.lastIndexOf(ConstantGeneral.FILE_SEPARATOR);
        String fileName = name.substring(index);

        String filePath = storage.getAbsolutePath() + ConstantGeneral.FILE_SEPARATOR + mainPackageName + fileName;


        File file = new File(filePath);
        if (file.exists()) {
            FileUtils.forceDelete(file);
        }
    }

    public List<File> getFiles() {
        return Arrays.asList(storage.listFiles());
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
        storage = new File(path);
        if (!storage.exists()) {
            storage.mkdirs();
        }
    }

    public String createPackages(List<String> packages) {
        String path = "";
        for (String pcg : packages) {
            path += pcg + ConstantGeneral.FILE_SEPARATOR;
        }

        path = path.substring(0, path.lastIndexOf(ConstantGeneral.FILE_SEPARATOR));
        return path;
    }

    public String setFileName(String fileName,String newFileName){
        int indexOfDot = fileName.lastIndexOf('.');
        newFileName += fileName.substring(indexOfDot);
        return newFileName;
    }
}
