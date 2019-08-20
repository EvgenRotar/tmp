package com.mastery.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.mastery.entity.User;
import com.mastery.exception.InvalidUserException;

@Component
public class UserValidator {

  public void validateUser(final User user) {
    if (user == null) {
      throw new InvalidUserException("User should not be null");
    }
    if (StringUtils.isBlank(user.getFirstName())) {
      throw new InvalidUserException("User firstName should not be null");
    }
    if (StringUtils.isBlank(user.getLastName())) {
      throw new InvalidUserException("User lastName should not be null");
    }
    if (StringUtils.isBlank(user.getDepartment())) {
      throw new InvalidUserException("User department should not be null");
    }
    if (StringUtils.isBlank(user.getJobTitle())) {
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
