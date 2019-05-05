package com.homeworks.dao;

import com.homeworks.config.AppConfig;
import com.homeworks.entity.User;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;

public class UserDaoImplTest extends TestCase {
    private static final String SQL_GET_COUNT = "SELECT count(*) FROM users";
    private static final String SQL_GET_NAME = "SELECT name FROM users WHERE id=?";

    private ApplicationContext applicationContext;
    private DataSource dataSource;
    private UserDao userDao;
    private User firstUser;
    private User secondUser;

    @Before
    private void init() {
        applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        dataSource = applicationContext.getBean(DataSource.class);
        userDao = applicationContext.getBean(UserDao.class);
        firstUser = new User();
        secondUser = new User();
        firstUser.setName("Alex");
        firstUser.setPassword("qwerty");
        firstUser.setBerthDate(LocalDate.of(1990,10,10));
        secondUser.setName("Sasha");
        secondUser.setPassword("1234");
        secondUser.setBerthDate(LocalDate.of(1995,5,5));



    }

    @Test
    public void testInsertUser() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int before = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        userDao.insertUser(firstUser);
        int after = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        assertFalse(before == after);
    }

    @Test
    public void testUpdateUserById(){
        String newName = "Lexa";
        firstUser.setName(newName);
        userDao.updateUserById(firstUser, firstUser.getId());
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String nameInDatabase = jdbcTemplate.queryForObject(SQL_GET_NAME, new Object[]{firstUser.getId()}, String.class);
        assertEquals(newName, nameInDatabase);
    }

    @Test
    public void testGetUserById() {
        User testUser =  userDao.getUserById(1L);
        assertNotNull(testUser);
        assertNotNull(firstUser.getName());
        assertNotNull(firstUser.getPassword());
        assertNotNull(firstUser.getBerthDate());
        assertNotNull(firstUser.getAge());
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = userDao.getAllUsers();
        assertFalse(users.isEmpty());
    }

    @Test
    public void testDeleteUserById() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        int before = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        userDao.deleteUserById(2L);
        int after = jdbcTemplate.queryForObject(SQL_GET_COUNT, Integer.class);
        assertFalse(before == after);
    }
}
