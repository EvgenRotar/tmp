package com.mastery.dao;

import com.mastery.entity.User;
import java.util.List;

import org.springframework.dao.DataAccessException;

//Java doc(да их пишут:)) на интерфейсах
/**
 * DAO interface for User.
 */
public interface UserDao {

    /**
     * Add new user to DB.
     *
     * @param user
     * @return User ID.
     * @throws DataAccessException
     */
    Long saveUser(User user) throws DataAccessException;

    //id обычно Long
    /**
     * Get user by ID.
     *
     * @param userId
     * @return User.
     * @throws DataAccessException
     */
    User getUserById(Long userId) throws DataAccessException;

    /**
     * Get all users.
     *
     * @return List of users.
     * @throws DataAccessException
     */
    List<User> findAll() throws DataAccessException;

    /**
     * Update user.
     *
     * @param user
     * @return void.
     * @throws DataAccessException
     */
    void updateUser(User user) throws DataAccessException;

    /**
     * Delete user from DB.
     *
     * @param userId
     * @return Count of deleted employees.
     * @throws DataAccessException
     */
    Integer deleteUser(Long userId) throws DataAccessException;

}
