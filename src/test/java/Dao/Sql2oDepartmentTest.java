package Dao;

import models.Department;
import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.sql.Connection;
import java.util.Arrays;

import static org.junit.Assert.*;

public class Sql2oDepartmentTest {
    private static Sql2oUserDao userDao;
    private static Sql2oDepartmentDao departmentDao;
    private static Sql2oNewsDao newsDao;
    private static Connection con;
    @Before
    public static void setUP() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT = RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        departmentDao = new Sql2oDepartmentDao(sql2o);
        userDao = new Sql2oUserDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        con = (Connection) sql2o.open();

        departmentDao.clearAll();
        userDao.clearAll();
    }
    @After
    public void tearDown() throws Exception{
        departmentDao.clearAll();
    }
    @Test
    public void getAllDepartments_ReturnAllDepartments_True(){
        Department testDepartment1 = setupDepartment();
        Department testDepartment2 = setupDepartment();

        departmentDao.addDepartment(testDepartment1);
        departmentDao.addDepartment(testDepartment2);

        assertEquals(2,departmentDao.getAllDepart().size());
        assertTrue(departmentDao.getAllDepart().containsAll(Arrays.asList(testDepartment1, testDepartment2)));
    }
    @Test
    public void getDepartmentUserById(){
        Department testDepartment1 = setupDepartment();
        Department testDepartment2 = setupDepartment();

        departmentDao.addDepartment(testDepartment1);
        departmentDao.addDepartment(testDepartment2);

        User testUser1 = setupUser();
        User testUser2 = setupUser();
        User testUser3 = setupUser();

        userDao.addUser(testUser1);
        userDao.addUser(testUser2);
        userDao.addUser(testUser3);

        userDao.updateUser(testUser1, testUser1.getName(), testUser1.getRole(), testUser1.getPosition(), testUser1.getDepartId());
        userDao.updateUser(testUser2, testUser2.getName(), testUser2.getRole(), testUser2.getPosition(), testUser2.getDepartId());
        userDao.updateUser(testUser3, testUser3.getName(), testUser3.getRole(), testUser3.getPosition(), testUser3.getDepartId());

        int dc = departmentDao.getAllDepart().size();
        int uc = userDao.getAllUsers().size();
        assertEquals(2,userDao.getDepartUserById(testDepartment1.getId()).size());
        assertTrue(departmentDao.getDepartUserById().containsAll(Arrays.asList(testDepartment1, testDepartment2)));

        assertEquals(1,departmentDao.getDepartUserById().contains(testUser1));
        assertTrue(departmentDao.getDepartUserById().containsAll(Arrays.asList(testUser2, testUser3)));
        assertEquals(false, departmentDao.getDepartUserById().contains(testUser1));
        assertEquals(false, departmentDao.getDepartUserById().contains(testUser2));
        assertEquals(false, departmentDao.getDepartUserById().contains(testUser3));
    }

    @Test
    public void addDepartment_addsDepartmentSetsId_True() {

        Department testDepartment1 = setupDepartment();
        Department testDepartment2 = setupDepartment();

        int ol_id = testDepartment1.getId();
        int ol_id2 = testDepartment2.getId();

        departmentDao.addDepartment(testDepartment1);
        departmentDao.addDepartment(testDepartment2);

        assertNotEquals(ol_id, testDepartment1.getId());
        assertNotEquals(ol_id2, testDepartment2.getId());
        assertTrue(testDepartment2.getId() > testDepartment1.getId());
        assertTrue(1 == testDepartment2.getId() - testDepartment1.getId());
    }

    @Test
    public void updateDepartment_updatesNameDescription_True() {
        Department testDepartment = setupDepartment();
        Department testDepartment2 = setupDepartment();

        departmentDao.addDepartment(testDepartment);
        departmentDao.addDepartment(testDepartment2);

        String ol_name = testDepartment.getName();
        String ol_desc =testDepartment.getDescription();

        String ol_name2 = testDepartment2.getName();
        String ol_desc2 =testDepartment2.getDescription();

        departmentDao.updateDepartment(testDepartment,"Technology","Efficiency");

        assertNotEquals(ol_name,testDepartment.getName());
        assertNotEquals(ol_desc,testDepartment.getDescription());

        assertEquals(ol_name2,testDepartment2.getName());
        assertEquals(ol_desc2,testDepartment2.getDescription());
    }

    @Test
    public void clearAllDepartments() {
        Department d = setupDepartment();
        Department d2 = setupDepartment();

        departmentDao.addDepartment(d);
        departmentDao.addDepartment(d2);

        assertEquals(2,departmentDao.getAllDepart().size());
        departmentDao.clearAll();
        assertEquals(0,departmentDao.getAllDepart().size());
    }

    private Department setupDepartment(){
        return new Department(1,"Finance","Everything accounting");
    }

    private User setupUser(){
        return new User(1,"Alan Tiren","Junior","Editor",2);
    }


}
