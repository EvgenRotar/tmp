package com.mastery.service.exception;

public class InvalidUserException extends RuntimeException {

  public InvalidUserException(String msg) {
    super(msg);
  }
}