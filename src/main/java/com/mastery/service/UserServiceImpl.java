package com.mastery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mastery.dao.UserDao;
import com.mastery.entity.User;
import com.mastery.service.exception.InvalidUserException;
import com.mastery.validators.UserValidator;

@Service
@Transactional
public class UserServiceImpl implements UserService {

  private final UserDao userDao;
  private final UserValidator userValidator;

  @Autowired
  public UserServiceImpl(UserDao userDao, UserValidator userValidator) {
    this.userDao = userDao;
    this.userValidator = userValidator;
  }

  @Override
  public List<User> findAll() {
    return userDao.findAll();
  }

  @Override
  public Long saveUser(User user) {
    //валидатор нужен
    userValidator.validateUser(user);

    return userDao.saveUser(user);
  }

  @Override
  public User getUserById(Long userId) {
    if (userId == null || userId <= 0) {
      throw new InvalidUserException("UserId should be more than 0 and should not be null");
    }

    return userDao.getUserById(userId);
  }

  @Override
  public void updateUser(User user) {
    userValidator.validateUser(user);

    userDao.updateUser(user);
  }

  @Override
  public Integer deleteUser(Long userId) {
    if (userId == null || userId <= 0) {
      throw new InvalidUserException("UserId should be more than 0 and should not be null");
    }

    return userDao.deleteUser(userId);
  }

}