package com.mastery.entity;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {

  //Все поля должны быть private и обращения через геттеры и сеттеры. Инкапсуляция.
  private Long userId;

  //Валидация
  @Size(min = 2, max = 20)
  private String firstName;

  @Size(min = 2, max = 20)
  private String lastName;

  @Size(min = 2, max = 20)
  private String department;

  @Size(min = 2, max = 20)
  private String jobTitle;

  //Пол лучше делать через энам
  @NotNull
  private Gender gender;

  @Past
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  private Date dateOfBirth;

  public User() {
  }

  public User(Long userId, String firstName) {
    this.userId = userId;
    this.firstName = firstName;
  }

  public static Builder builder() {
    return new Builder();
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long id) {
    this.userId = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(String jobTitle) {
    this.jobTitle = jobTitle;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public static class Builder {

    private User user;

    Builder() {
      this.user = new User();
    }

    public Builder userId(Long userId) {
      user.setUserId(userId);
      return this;
    }

    public Builder firstName(String firstName) {
      user.setFirstName(firstName);
      return this;
    }

    public Builder lastName(String lastName) {
      user.setLastName(lastName);
      return this;
    }

    public Builder department(String department) {
      user.setDepartment(department);
      return this;
    }

    public Builder jobTitle(String jobTitle) {
      user.setJobTitle(jobTitle);
      return this;
    }

    public Builder gender(Gender gender) {
      user.setGender(gender);
      return this;
    }

    public Builder dateOfBirth(Date dateOfBirth) {
      user.setDateOfBirth(dateOfBirth);
      return this;
    }

    public User build() {
      return user;
    }

  }

}