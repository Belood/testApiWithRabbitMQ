package com.belood.MQSender.api.exception;

import lombok.Getter;

@Getter
public class ApiError {
  private final String code;
  private final String message;

  public ApiError(String code, String message){
    this.code = code;
    this.message = message;
  }

}
