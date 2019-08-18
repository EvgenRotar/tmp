package com.mastery.validators;

import org.springframework.stereotype.Component;

import com.mastery.entity.User;
import com.mastery.service.exception.InvalidUserException;

@Component
public class UserValidator {

  public void validateUser(final User user) {
    if (user == null) {
      throw new InvalidUserException("User should not be null");
    }
    if (user.getFirstName() == null) {
      throw new InvalidUserException("User firstName should not be null");
    }
    if (user.getLastName() == null) {
      throw new InvalidUserException("User lastName should not be null");
    }
    if (user.getDepartment() == null) {
      throw new InvalidUserException("User department should not be null");
    }
    if (user.getJobTitle() == null) {
      throw new InvalidUserException("User jobTitle should not be null");
    }
    if (user.getGender() == null) {
      throw new InvalidUserException("User gender should not be null");
    }
    if (user.getDateOfBirth() == null) {
      throw new InvalidUserException("User dateOfBirth should not be null");
    }
  }

}
