package service;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.EntityNotFoundException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.department.FacultyDegree;
import com.university.model.department.lcp.DocumentType;
import com.university.model.general.lcp.Language;
import com.university.web.util.ConstantGeneral;
import general.ShellTest;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 04.08.13
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */
public class FacultyDegreeManagerTest extends ShellTest {
    @Test
    public void add() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getById2() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getAllNames() {
    }

    @Test
    public void getByParams() {
        Language language = Language.ARMENIAN;

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("langId", language.getValue());
        params.put("docTypeId", DocumentType.DIPLOMA.getId());

        try {
            List<FacultyDegree> facultyDegrees = facultyDegreeManager.getByParams(params);

            System.out.println(facultyDegrees.size());

            for (FacultyDegree facultyDegree : facultyDegrees) {
                System.out.println(facultyDegree.getFaculty());
                System.out.println(facultyDegree.getDegree());
                System.out.println(facultyDegree.getDocument());
                System.out.println();
            }


        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    public void edit() {
    }

    @Test
    public void remove() {
    }
}
