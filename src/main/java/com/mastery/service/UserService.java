package com.mastery.service;

import java.util.List;

import com.mastery.entity.User;


public interface UserService {

  /**
   * Get all users.
   *
   * @return List of users.
   */
  List<User> findAll();

  /**
   * Add new user to DB.
   *
   * @param user
   * @return User ID.
   */
  Long saveUser(User user);

  /**
   * Get user by ID.
   *
   * @param userId
   * @return User.
   */
  User getUserById(Long userId);

  /**
   * Update user.
   *
   * @param user
   * @return void.
   */
  void updateUser(User user);

  /**
   * Delete user from DB.
   *
   * @param userId
   * @return Count of deleted users.
   */
  Integer deleteUser(Long userId);

}