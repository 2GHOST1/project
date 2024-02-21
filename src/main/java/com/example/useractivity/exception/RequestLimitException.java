package com.example.useractivity.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class RequestLimitException extends  RuntimeException {
    private  static  final  long serailVersionUID = 1;
    private  String message;
}
