package com.mastery.service;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mastery.dao.UserDao;
import com.mastery.entity.Gender;
import com.mastery.entity.User;
import com.mastery.exception.InvalidUserException;
import com.mastery.validator.UserValidator;

public class UserServiceImplTest {

  @Mock
  private UserDao daoMock;

  @Mock
  private UserValidator userValidatorMock;

  @InjectMocks
  private UserServiceImpl userService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void saveUserTest() {
    User user = buildUser();
    userService.saveUser(user);

    verify(userValidatorMock, times(1)).validateUser(user);
    verify(daoMock).saveUser(user);
  }

  @Test
  public void getUserByIdTest() {
    User userTest = buildUser();
    when(daoMock.getUserById(1L)).thenReturn(userTest);

    User user = userService.getUserById(1L);

    Assert.assertEquals(userTest, user);
  }

  @Test(expected = InvalidUserException.class)
  public void getUserWithZeroIdTest() {
    userService.getUserById(0L);

    verify(daoMock, never()).getUserById(0L);
  }

  @Test
  public void updateUserTest() {
    User user = buildUser();
    userService.updateUser(user);

    verify(userValidatorMock, times(1)).validateUser(user);
    verify(daoMock).updateUser(user);
  }

  @Test
  public void deleteUserTest() {
    when(daoMock.deleteUser(1L)).thenReturn(1);

    Integer count = userService.deleteUser(1L);

    Assert.assertEquals(new Integer(1), count);
    verify(daoMock, times(1)).deleteUser(1L);
  }

  @Test(expected = InvalidUserException.class)
  public void deleteUserWithZeroIdTest() {
    userService.deleteUser(0L);

    verify(daoMock, never()).deleteUser(0L);
  }

  @Test
  public void listUserTest() {
    when(daoMock.findAll()).thenReturn(Collections.singletonList(buildUser()));

    List<User> users = userService.findAll();
    Assert.assertEquals(1, users.size());
    verify(daoMock).findAll();
  }

  private User buildUser() {
    return User.builder()
        .userId(1L)
        .firstName("first_name")
        .lastName("last_name")
        .department("department_name")
        .jobTitle("job_title")
        .gender(Gender.MALE)
        .dateOfBirth(new Date())
        .build();
  }
}
