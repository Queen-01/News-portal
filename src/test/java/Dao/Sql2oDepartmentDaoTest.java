//package Dao;
//
//import models.Department;
//import models.DepartNews;
//import models.User;
//import org.junit.*;
//import org.sql2o.Connection;
//import org.sql2o.Sql2o;
//
//import java.sql.Timestamp;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class Sql2oDepartmentDaoTest {
//
//    private static  Sql2oDepartmentDao departmentDao;
//    private static Sql2oUserDao userDao;
//    private static Sql2oNewsDao newsDao;
//    private static Connection con;
//    @BeforeClass
//    public static void setUp() throws Exception {
//        String connectionString = "jdbc:h2:mem:testing;INIT = RUNSCRIPT from 'classpath:db/create.sql'";
//        Sql2o sql2o = new Sql2o(connectionString,"moringa","access");
//
//        departmentDao = new Sql2oDepartmentDao(sql2o) {
////            @Override
////            public List<User> getDepartUserById() {
////                return null;
////            }
//        };
//        userDao = new Sql2oUserDao (sql2o);
//        newsDao = new Sql2oNewsDao(sql2o);
//        con = sql2o.open();
//
//        departmentDao.clearAll(); //start with empty table
//        userDao.clearAll();
//    }
//
//    @After
//    public void tearDown() throws Exception { departmentDao.clearAll(); userDao.clearAll();}
//
//    @AfterClass
//    public static void shutDown() throws Exception { con.close(); }
//
//    @Test
//    public void getAllDepartments_ReturnsAllDepartments_True() {
//        Department d1 = setupDepartment();
//        Department d2 = setupDepartment();
//
//        departmentDao.addDepartment(d1);
//        departmentDao.addDepartment(d2);
//
//        assertEquals(2, departmentDao.getAllDepart().size());
//        assertTrue(departmentDao.getAllDepart().containsAll(Arrays.asList(d1,d2)));
//    }
//
////    @Test
////    public void getDepartmentUsersById_ReturnsDepartmentsUsers_True() {
////
////        Department d1 = setupDepartment();
////        Department d2 = setupDepartment();
////
////        departmentDao.addDepartment(d1);
////        departmentDao.addDepartment(d2);
////
////        User user1 = setupUser();
////        User user2 = setupUser();
////        User user3 = setupUser();
////        User user4 = setupUser();
////        userDao.addUser(user1);
////        userDao.addUser(user2);
////        userDao.addUser(user3);
////        userDao.addUser(user4);
////
////        userDao.updateUser(user1,user1.getName(),user1.getPosition(),user1.getRole(),d1.getId());
////        userDao.updateUser(user2,user2.getName(),user2.getPosition(),user2.getRole(),d1.getId());
////        userDao.updateUser(user3,user3.getName(),user3.getPosition(),user3.getRole(),d2.getId());
////
////        int dc = departmentDao.getAllDepart().size();
////        int uc = userDao.getAllUsers().size();
////
////        assertEquals(2, departmentDao.getDepartUserById().size());
////        assertTrue(departmentDao.getDepartUserById().containsAll(Arrays.asList(user1,user2)));
////
////
////        assertEquals(1, departmentDao.getDepartUserById().size());
////        assertTrue(departmentDao.getDepartUserById().containsAll(Arrays.asList(user3)));
////        assertFalse(departmentDao.getDepartUserById().contains(user1));
////        assertFalse(departmentDao.getDepartUserById().contains(user2));
////        assertFalse(departmentDao.getDepartUserById().contains(user4));
////    }
//
//    @Test
//    public void getDepartmentNewsById_ReturnsDepartmentsNews_True() {
//        Department d1 = setupDepartment();
//        Department d2 = setupDepartment();
//
//        departmentDao.addDepartment(d1);
//        departmentDao.addDepartment(d2);
//
//        DepartNews dn = setupDepartNews();
//        DepartNews dn2 = setupDepartNews();
//        DepartNews dn3 = setupDepartNews();
//        DepartNews dn4 = setupDepartNews();
//
//        newsDao.addDepartNews(dn);
//        newsDao.addDepartNews(dn2);
//        newsDao.addDepartNews(dn3);
//
//        newsDao.updateDepartNews(dn,dn.getUserId(),dn.getContent(),d1.getId());
//        newsDao.updateDepartNews(dn2,dn2.getUserId(),dn2.getContent(),d1.getId());
//        newsDao.updateDepartNews(dn3,dn3.getUserId(),dn3.getContent(),d2.getId());
//
//        assertEquals(2,departmentDao.getDepartNewsById().size());
//        assertTrue(departmentDao.getDepartNewsById().containsAll(Arrays.asList(dn,dn2)));
//
//        assertEquals(1,departmentDao.getDepartNewsById().size());
//        assertTrue(departmentDao.getDepartNewsById().contains(dn3));
//        assertFalse(departmentDao.getDepartNewsById().contains(dn));
//        assertFalse(departmentDao.getDepartNewsById().contains(dn2));
//        assertFalse(departmentDao.getDepartNewsById().contains(dn4));
//
//    }
//
//    @Test
//    public void addDepartment_addsDepartmentSetsId_True() {
//
//        Department d1 = setupDepartment();
//        Department d2 = setupDepartment();
//
//        int ol_id = d1.getId();
//        int ol_id2= d2.getId();
//
//       departmentDao.addDepartment(d1);
//        departmentDao.addDepartment(d2);
//
//        assertNotEquals(ol_id,d1.getId());
//        assertNotEquals(ol_id2,d2.getId());
//        assertTrue(d2.getId() >  d1.getId());
//        assertTrue(1 == d2.getId() - d1.getId());
//
//    }
//
//    @Test
//    public void findDepartmentById_findsCorrectDepartment_True() {
//        Department d1 = setupDepartment();
//        Department d2 = setupDepartment();
//
//        departmentDao.addDepartment(d1);
//        departmentDao.addDepartment(d2);
//
//        Department foundDpt = departmentDao.findDepartById(d1.getId());
//
//        assertEquals(foundDpt,d1);
//    }
//
//    @Test
//    public void updateDepartment_updatesNameDescription_True() {
//        Department d = setupDepartment();
//        Department d2 = setupDepartment();
//
//        departmentDao.addDepartment(d);
//        departmentDao.addDepartment(d2);
//
//        String ol_name = d.getName();
//        String ol_desc =d.getDescription();
//
//        String ol_name2 = d2.getName();
//        String ol_desc2 =d2.getDescription();
//
//        departmentDao.updateDepartment(d,"Technology","Efficiency");
//
//        assertNotEquals(ol_name,d.getName());
//        assertNotEquals(ol_desc,d.getDescription());
//
//        assertEquals(ol_name2,d2.getName());
//        assertEquals(ol_desc2,d2.getDescription());
//    }
//
//    @Test
//    public void clearAllDepartments() {
//        Department d = setupDepartment();
//        Department d2 = setupDepartment();
//
//        departmentDao.addDepartment(d);
//        departmentDao.addDepartment(d2);
//
//        assertEquals(2,departmentDao.getAllDepart().size());
//        departmentDao.clearAll();
//        assertEquals(0,departmentDao.getAllDepart().size());
//    }
//
//    private Department setupDepartment(){
//        return new Department(1,"Finance","Everything accounting");
//    }
//
//    private User setupUser(){
//        return new User(1,"Ann","Senior","CFO",1);
//    }
//
//    private DepartNews setupDepartNews(){
//        return new DepartNews(1,1,Sql2oNewsDao.Depart_news,"Oceans",new Timestamp(new Date().getTime()),1);
//    }
//}
