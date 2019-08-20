package com.mastery.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mastery.entity.Gender;
import com.mastery.entity.User;
import com.mastery.service.UserServiceImpl;

public class UserControllerTest {

  @InjectMocks
  private UserController userController;

  private MockMvc mockMvc;
  private DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
  private ObjectMapper objectMapper = new ObjectMapper();

  @Mock
  private UserServiceImpl service;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
  }

  @Test
  public void findAllTest() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(new User(1L, "Ivany", "Ivanov", "department", "Profi", Gender.MALE, sdf.parse("1990-05-19")));
    users.add(new User(2L, "Sergey", "Ivanov", "department", "Profi", Gender.MALE, sdf.parse("1993-06-17")));

    when(service.findAll()).thenReturn(users);

    mockMvc.perform(
        get("/users")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(users)))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));

    verify(service).findAll();
  }

  @Test
  public void getUserByIdTest() throws Exception {
    User user = new User(1L, "Ivany", "Ivanov", "department", "Profi", Gender.MALE, sdf.parse("1990-05-19"));

    when(service.getUserById(anyLong())).thenReturn(user);

    mockMvc.perform(
        get("/users/1")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(content().json(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());

    verify(service).getUserById(1L);
  }

  @Test
  public void addUserTest() throws Exception {
    String user = objectMapper.writeValueAsString(
        new User(1L, "Ivany", "Ivanov", "department", "Profi", Gender.MALE, sdf.parse("1990-05-19")));

    when(service.saveUser(any(User.class))).thenReturn(3L);

    mockMvc.perform(
        post("/users")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
            .content(user)
    ).andDo(print())
        .andExpect(status().isCreated());

    verify(service).saveUser(any(User.class));
  }

  @Test
  public void updateUserTest() throws Exception {
    String user = objectMapper.writeValueAsString(new User(1L, "Ivany", "Ivanov", "department",
        "Profi", Gender.MALE, sdf.parse("1990-05-19")));

    doNothing().when(service).updateUser(any(User.class));

    mockMvc.perform(
        put("/users/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(user)
    ).andDo(print())
        .andExpect(status().isAccepted());

    verify(service).updateUser(any(User.class));
  }

  @Test
  public void deleteUserTest() throws Exception {

    when(service.deleteUser(anyLong())).thenReturn(2);

    mockMvc.perform(
        delete("/users/2")
            .accept(MediaType.APPLICATION_JSON)
    ).andDo(print())
        .andExpect(status().isOk());

    verify(service).deleteUser(anyLong());
  }

}