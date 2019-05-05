package com.homeworks.dao;

import com.homeworks.entity.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Qualifier("userDao")
public class UserDaoImpl implements UserDao {
    private final static Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_INSERT_USER = "INSERT INTO users (name, password, date_of_creation, birth_date, age) VALUES(:name,:password,:date_of_creation,:birth_date,:age)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET name=:name, password=:password, date_of_creation=:date_of_creation, birth_date=:birth_date,age=:age WHERE id=:id";
    private static final String SQL_DELETE_USER_BY_ID = "DELETE FROM users WHERE id=:id";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id=:id";
    private static final String SQL_SELECT_ALL = "SELECT * FROM users";
    private static final String SQL_SELECT_ALL_BY_DATE_OF_CREATION = "SELECT * FROM users WHERE date_of_creation=:date_of_creation";

//    @Autowired
//    public void setJdbcTemplate(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }

    @Override
    public void insertUser(User user) {
        jdbcTemplate.update(SQL_INSERT_USER, user.getName(), user.getPassword(), user.getDateOfCreation(), user.getBerthDate(), user.getAge());
        LOGGER.info("User added");
    }

    @Override
    public void updateUserById(User user, Long userId) {
        jdbcTemplate.update(SQL_UPDATE_USER, user.getName(), user.getPassword(), user.getDateOfCreation(), user.getBerthDate(), user.getAge(), userId);
        LOGGER.info("User updated");

    }

    @Override
    public void deleteUserById(Long userId) {
        jdbcTemplate.update(SQL_DELETE_USER_BY_ID, userId);
        LOGGER.info("User deleted");

    }

    @Override
    public User getUserById(Long userId) {
        User user = jdbcTemplate.queryForObject(SQL_GET_USER_BY_ID, new Object[]{userId}, new BeanPropertyRowMapper<>(User.class));
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper(User.class));
        return users;
    }

    @Override
    public List<User> getUsersByDateOfCreation(LocalDate date) {
        List<User> usersByDateOfCreation = jdbcTemplate.query(SQL_SELECT_ALL_BY_DATE_OF_CREATION, new BeanPropertyRowMapper<>(User.class));
        return usersByDateOfCreation;
    }
}
