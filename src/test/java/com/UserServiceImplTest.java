//package com;
//
//import static org.junit.Assert.assertEquals;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import com.mastery.dao.UserDaoImpl;
//import com.mastery.entity.User;
//import com.mastery.service.UserServiceImpl;
//
//public class UserServiceImplTest {
//
//  @Mock
//  private UserDaoImpl daoMock;
//
//  @InjectMocks
//  private UserServiceImpl serviceMock;
//
//  @Before
//  public void setUp() {
//    MockitoAnnotations.initMocks(this);
//  }
//
//  @Test
//  public void saveUserTest() {
//    User user = new User();
//    serviceMock.save(user);
//    verify(daoMock).save(user);
//  }
//
//  @Test
//  public void saveIdTest() {
//    // create exception
//    RuntimeException exception = new RuntimeException("id - zero");
//    doThrow(exception).when(daoMock).getById(0);
//  }
//
//  @Test
//  public void updateUserTest() {
//    User user = new User();
//    serviceMock.update(user);
//    verify(daoMock).update(user);
//  }
//
//  @Test
//  public void deleteUserTest() {
//    when(daoMock.getById(1)).thenReturn(new User(1, "Siarhei"));
//    User user = serviceMock.getById(1);
//    serviceMock.delete(user.id);
//    verify(daoMock).delete(user.id);
//  }
//
//  @Test
//  public void IdTest() {
//    when(daoMock.getById(1)).thenReturn(new User(1, "Siarhei"));
//    User user = serviceMock.getById(1);
//    assertEquals("Siarhei", user.getFirstName());
//  }
//
//  @Test
//  public void listUserTest() {
//    List<User> list = new ArrayList<User>();
//    User user_one = new User(1, "Dima");
//    User user_two = new User(2, "Slava");
//    User user_three = new User(3, "Siarhei");
//
//    list.add(user_one);
//    list.add(user_two);
//    list.add(user_three);
//
//    when(daoMock.findAll()).thenReturn(list);
//
//    List<User> userList = serviceMock.findAll();
//    assertEquals(3, userList.size());
//    verify(daoMock).findAll();
//  }
//
//}
