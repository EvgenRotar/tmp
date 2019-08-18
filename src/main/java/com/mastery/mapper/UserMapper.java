package com.mastery.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mastery.entity.Gender;
import com.mastery.entity.User;

public class UserMapper implements RowMapper<User> {

  @Override
  public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
    //если 5+ сеттеров лучше использовать паттерн Билдер
    return User.builder()
        .userId(resultSet.getLong("user_id"))
        .firstName(resultSet.getString("first_name"))
        .lastName(resultSet.getString("last_name"))
        .department(resultSet.getString("department_name"))
        .jobTitle(resultSet.getString("job_title"))
        .gender(Gender.valueOf(resultSet.getString("gender")))
        .dateOfBirth(resultSet.getDate("date_of_birth"))
        .build();
  }

}