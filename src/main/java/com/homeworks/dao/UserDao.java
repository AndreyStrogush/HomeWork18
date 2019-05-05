package com.homeworks.dao;

import com.homeworks.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserDao {
    void insertUser(User user);

    void updateUserById(User user, Long userId);

    void deleteUserById(Long userId);

    User getUserById(Long userId);

    List<User> getAllUsers();

    List<User> getUsersByDateOfCreation(LocalDate date);
}
