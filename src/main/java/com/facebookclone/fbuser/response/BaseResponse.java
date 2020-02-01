package com.facebookclone.fbuser.response;


import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T>
{
    private Boolean success;
    private String  errorMessage;
    private T data;
    private HttpStatus httpStatus;

}
