package com.mastery.dao;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mastery.entity.User;
import com.mastery.mapper.UserMapper;

@Transactional
@Repository
@PropertySource(value = {"classpath:sql.properties"})
public class UserDaoImpl implements UserDao {

  private static final String USER_ID = "userId";
  private static final String FIRST_NAME = "firstName";
  private static final String LAST_NAME = "lastName";
  private static final String DEPARTMENT_NAME = "department";
  private static final String JOB_TITLE = "jobTitle";
  private static final String GENDER = "gender";
  private static final String DATE_OF_BIRTH = "dateOfBirth";

  private final NamedParameterJdbcTemplate jdbcTemplate;

  @Value("${UserSql.findAll}")
  private String findAllSql;
  @Value("${UserSql.getUserById}")
  private String getUserByIdSql;
  @Value("${UserSql.saveUser}")
  private String saveUserSql;
  @Value("${UserSql.updateUser}")
  private String updateUserSql;
  @Value("${UserSql.deleteUser}")
  private String deleteUserSql;

  @Autowired
  public UserDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Long saveUser(User user) throws DataAccessException {
    KeyHolder keyHolder = new GeneratedKeyHolder();
    MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    namedParameters.addValue(FIRST_NAME, user.getFirstName());
    namedParameters.addValue(LAST_NAME, user.getLastName());
    namedParameters.addValue(DEPARTMENT_NAME, user.getDepartment());
    namedParameters.addValue(JOB_TITLE, user.getJobTitle());
    namedParameters.addValue(GENDER, user.getGender().getValue());
    namedParameters.addValue(DATE_OF_BIRTH, user.getDateOfBirth());

    jdbcTemplate.update(saveUserSql, namedParameters, keyHolder, new String[]{"user_id"});

    return Objects.requireNonNull(keyHolder.getKey()).longValue();
  }

  @Override
  public User getUserById(Long userId) throws DataAccessException {
    return jdbcTemplate.queryForObject(getUserByIdSql, new MapSqlParameterSource(USER_ID, userId), new UserMapper());
  }

  public List<User> findAll() throws DataAccessException {
    return jdbcTemplate.query(findAllSql, new UserMapper());
  }

  @Override
  public void updateUser(User user) throws DataAccessException {
    MapSqlParameterSource namedParameters = new MapSqlParameterSource();

    namedParameters.addValue(USER_ID, user.getUserId());
    namedParameters.addValue(FIRST_NAME, user.getFirstName());
    namedParameters.addValue(LAST_NAME, user.getLastName());
    namedParameters.addValue(DEPARTMENT_NAME, user.getDepartment());
    namedParameters.addValue(JOB_TITLE, user.getJobTitle());
    namedParameters.addValue(GENDER, user.getGender().getValue());
    namedParameters.addValue(DATE_OF_BIRTH, user.getDateOfBirth());

    jdbcTemplate.update(updateUserSql, namedParameters);
  }

  @Override
  public Integer deleteUser(Long userId) {
    return jdbcTemplate.update(deleteUserSql, new MapSqlParameterSource(USER_ID, userId));
  }
}
