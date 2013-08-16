package general;

import com.university.data_access.dao.account.IUserDao;
import com.university.data_access.service.account.ICourseGroupManager;
import com.university.data_access.service.account.IUserManager;
import com.university.data_access.service.department.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

/**
 * User: Seryozha Hovhannisyan
 * Date: 6/6/13
 * Time: 2:59 AM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
/*@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)*/
@Transactional(readOnly = false)
public class ShellTest {

    @Autowired
    protected IUniversityManager universityManager;

    @Autowired
    protected IDepartmentManager departmentManager;

    @Autowired
    protected IReportManager reportManager;

    @Autowired
    protected IFacultyDegreeManager facultyDegreeManager;

    @Autowired
    protected IFacultyManager facultyManager;

    @Autowired
    protected ICourseGroupManager courseGroupManager;

    @Autowired
    protected IUserManager userManager;

    @BeforeClass
    public static void init() {
    }

    @Before
    public void beforeMethods() {
        Assert.notNull(universityManager);
        Assert.notNull(departmentManager);
        Assert.notNull(reportManager);
        Assert.notNull(facultyDegreeManager);
        Assert.notNull(facultyManager);
        Assert.notNull(courseGroupManager);
        Assert.notNull(userManager);
    }

}