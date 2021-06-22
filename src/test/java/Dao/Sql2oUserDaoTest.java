package Dao;

import models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Sql2oUserDaoTest {
    private Connection con;
    private Sql2oUserDao userDao;

    @Before
    public void setUp() throws Exception{
        String connectionString = "jdbc:h2:mem:testing;INIT = RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");

        userDao = new Sql2oUserDao(sql2o);
        con = sql2o.open();
        userDao.clearAll();
    }
    @After
    public void tearDown() throws Exception{
        con.close();
    }
    @Test
    public void addingUserSetsId() throws Exception{
        User testUser1 = setUpUser();
        User testUser2 = setUpUser();

        int testUser1_id = testUser1.getId();
        int testUser2_id = testUser2.getId();

        userDao.addUser(testUser1);
        userDao.addUser(testUser2);

        assertTrue(testUser1_id != testUser1.getId());
        assertTrue(testUser2_id != testUser2.getId());
        assertTrue(testUser2.getId()>testUser1.getId());
        assertTrue(1 == testUser2.getId()-testUser1.getId());
        assertTrue(userDao.getAllUsers().contains(Arrays.asList(testUser1, testUser2)));
    }
    @Test
    public void getAllUsers_ReturnAllUsers_True() throws Exception{
        User testUser1 = setUpUser();
        User testUser2 = setUpUser();

        userDao.addUser(testUser1);
        userDao.addUser(testUser2);
        assertEquals(2,userDao.getAllUsers().size());
        assertTrue(userDao.getAllUsers().contains(Arrays.asList(testUser1, testUser2)));
    }
    @Test
    public void findUserById_ReturnsCorrectUser_True() {
        User testUser1 = setUpUser();
        User testUser2 = setUpUser();
        userDao.addUser(testUser1);
        userDao.addUser(testUser2);

        User foundUser = userDao.findUserById(testUser1.getId());
        assertEquals(testUser1,foundUser);

    }
    @Test
    public void updateUser_UpdatesUserNamePositionRoleDepartId_True() throws Exception{
        User testUser1 = setUpUser();
        User testUser2 = setUpUser();
        userDao.addUser(testUser1);
        userDao.addUser(testUser2);

        String ol_name = testUser1.getName();
        String ol_position = testUser1.getPosition();
        int ol_depart = testUser1.getDepartId();
    }

    private User setUpUser() {
        return null;
    }

}
