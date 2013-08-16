package com.university.data_access.service.department.impl;

import com.university.data_access.dao.department.IDocumentDao;
import com.university.data_access.dao.department.IFacultyDegreeDao;
import com.university.data_access.exception.DatabaseException;
import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.data_access.service.department.IFacultyDegreeManager;
import com.university.data_access.util.DataStorage;
import com.university.model.department.Document;
import com.university.model.department.DocumentInfo;
import com.university.model.department.FacultyDegree;
import com.university.web.util.ConstantGeneral;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 29.07.13
 * Time: 5:04
 * To change this template use File | Settings | File Templates.
 */
@Transactional(readOnly = true)
public class FacultyDegreeManagerImpl implements IFacultyDegreeManager {

    private IFacultyDegreeDao facultyDegreeDao;
    private IDocumentDao documentDao;
    private DataStorage dataStorage;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void add(List<FacultyDegree> facultyDegrees, List<File> files, String path, List<String> packageNames, List<String> fileNames) throws InternalErrorException, DuplicateDataException {
        //dataStorage.setPath(path);
        String logoPath = null;
        try {

            for (int i = 0; i < facultyDegrees.size(); i++) {
                /*Store the document data*/

                dataStorage.setPath(path);
                String pcg = dataStorage.createPackages(packageNames);
                pcg +=  ConstantGeneral.FILE_SEPARATOR + facultyDegrees.get(i).getDegree().name()+
                        ConstantGeneral.FILE_SEPARATOR + facultyDegrees.get(i).getDocument().getDocumentType().name();

                String fileName = fileNames.get(i);

                fileName = dataStorage.setFileName(fileName,facultyDegrees.get(i).getDocument().getDocumentType().name())  ;

                String dataPath = ConstantGeneral.MAIN_PATH + ConstantGeneral.FILE_SEPARATOR +
                        dataStorage.add(files.get(i), pcg, fileName);

                facultyDegrees.get(i).getDocument().setPath(dataPath);

                /*insert document*/
                Document document = facultyDegrees.get(i).getDocument();
                documentDao.add(document);
                /*insert documentInfos*/
                List<DocumentInfo> documentInfos = document.getDocumentInfos();
                for (DocumentInfo documentInfo : documentInfos) {
                    documentInfo.setDocumentId(document.getId());
                }
                documentDao.addInfos(documentInfos);
                /*insert faculty degree*/
                facultyDegreeDao.add(facultyDegrees.get(i));
            }


        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Override
    public FacultyDegree getById(int id) throws InternalErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FacultyDegree getById(int id, int langId) throws InternalErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getAll(int langId) throws InternalErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getAllNames(int langId) throws InternalErrorException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<FacultyDegree> getByParams(Map<String, Object> params) throws InternalErrorException {
        try {
            return facultyDegreeDao.getByParams(params);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void edit(FacultyDegree facultyDegree, File file, String path, String packageName, String fileName) throws InternalErrorException, EntityNotFoundException {
        dataStorage.setPath(path);
        try {
            dataStorage.remove(packageName, fileName);
            String logoPath = dataStorage.add(file, packageName, fileName);
            facultyDegree.getDocument().setPath(logoPath);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    @Override
    public void remove(int id, String packageName, String path) throws InternalErrorException, EntityNotFoundException {
        try {
            FacultyDegree facultyDegree = facultyDegreeDao.getById(id);

            dataStorage.setPath(path);
            dataStorage.remove(path, facultyDegree.getDocument().getPath());
            facultyDegreeDao.remove(id);
        } catch (DatabaseException e) {
            throw new InternalErrorException(e);
        } catch (IOException e) {
            throw new InternalErrorException(e);
        }
    }


    public void setFacultyDegreeDao(IFacultyDegreeDao facultyDegreeDao) {
        this.facultyDegreeDao = facultyDegreeDao;
    }

    public void setDocumentDao(IDocumentDao documentDao) {
        this.documentDao = documentDao;
    }

    public void setDataStorage(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }
}
