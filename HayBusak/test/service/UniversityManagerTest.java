package service;

import com.university.data_access.exception.DuplicateDataException;
import com.university.data_access.exception.InternalErrorException;
import com.university.model.department.University;
import com.university.model.department.UniversityInfo;
import com.university.model.general.lcp.Language;
import general.ShellTest;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Serozh
 * Date: 20.06.13
 * Time: 22:58
 * To change this template use File | Settings | File Templates.
 */
public class UniversityManagerTest extends ShellTest {


    @Test
     /*Create*/   /*+*/
    public void add() {


        for (int i = 0; i < 100; i++) {
            University university = new University();

            university.setEmail(i+"university@setEmail.ru");
            university.setLogoPath( i+"university setLogoPath");
            university.setPhone(i+"university setPhone");
            university.setLink(i+"university link");

            List<UniversityInfo> infos = new ArrayList<UniversityInfo>();

            UniversityInfo infoARMENIAN = new UniversityInfo();
            infoARMENIAN.setAddress("UniversityInfo setAddress ARMENIAN "+i);
            infoARMENIAN.setName("UniversityInfo setName ARMENIAN "+i);
            infoARMENIAN.setAbbreviation("HY"+i);
            infoARMENIAN.setInfo("UniversityInfo setInfo ARMENIAN "+i);

            infoARMENIAN.setLanguage(Language.ARMENIAN);

            UniversityInfo infoENGLISH = new UniversityInfo();
            infoENGLISH.setAddress("Armenia c.Yerevan Aleq Manukyan "+i);
            infoENGLISH.setName("Yerevan State University "+i);
            infoENGLISH.setAbbreviation("YS"+i);
            infoENGLISH.setInfo("Long Test "+i);
            infoENGLISH.setLanguage(Language.ENGLISH);


            UniversityInfo infoRUSSIAN = new UniversityInfo();
            infoRUSSIAN.setAddress("UniversityInfo setAddress RUSSIAN "+i);
            infoRUSSIAN.setName("UniversityInfo setName RUSSIAN "+i);
            infoRUSSIAN.setAbbreviation("ru"+i);
            infoRUSSIAN.setInfo("UniversityInfo setInfo RUSSIAN "+i);
            infoRUSSIAN.setLanguage(Language.RUSSIAN);


            UniversityInfo infoFRANCE = new UniversityInfo();
            infoFRANCE.setAddress("UniversityInfo setAddress FRANCE "+i);
            infoFRANCE.setName("UniversityInfo setName FRANCE "+i);
            infoFRANCE.setAbbreviation("fr"+i);
            infoFRANCE.setInfo("UniversityInfo setInfo FRANCE "+i);
            infoFRANCE.setLanguage(Language.FRANCE);

            infos.add(infoARMENIAN);
            infos.add(infoENGLISH);
            infos.add(infoRUSSIAN);
            infos.add(infoFRANCE);

            university.setUniversityInfos(infos);
            try {
                universityManager.add(university, null, "", "");
            } catch (InternalErrorException e) {
                e.printStackTrace();
            } catch (DuplicateDataException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    /*Read*/  /*+*/
    public void getById() {
        try {
            University university = universityManager.getById(1);
            System.out.println(university);
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test
    /*Read*/  /*+*/
    public void getByIdLang() {
        try {
            University university = universityManager.getById(1, Language.ENGLISH.getValue());
            System.out.println(university);
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Test  /*+*/
    public void getAll() {
        try {
            List<University> universities = universityManager.getAll(Language.ENGLISH.getValue());

            for (University university : universities) {
                System.out.println(university);
            }

        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    @Test
    public void getByParams() {

        try {
            List<University> universities = universityManager.getByParams(null);

            for (University university : universities) {
                System.out.println(university);
            }

        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    /*Edit*/
    @Test
    public void editDepartment() {
        /*try {
            University university = universityManager.getById(8);

            university.setEmail("seryozha.hovhannisyan@mail.ru");
            university.setLogoPath("logo path1211");
            university.setPhone("787377");

            *//*university.setAddress("ՀՀ ք.Երևան Ալեք Մանուկյան 85");
            university.setName("Երևանի Պետական Համալսարանը ");
            university.setInfo("Երկար տեստը");
            university.setLanguage(Language.ARMENIAN);*//*


            universityManager.edit(university);

        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (EntityNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }

    @Test
    public void editInfo() {
    }

    /*Delete*/
    @Test
    public void remove() {
        /*try {
            universityManager.remove(1);
        } catch (InternalErrorException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (EntityNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }*/
    }
}
