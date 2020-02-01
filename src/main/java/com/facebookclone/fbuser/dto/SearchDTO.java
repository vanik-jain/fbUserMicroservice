package com.facebookclone.fbuser.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;



@Getter
@Setter
@ToString
public class SearchDTO
{
    private String userId;
    private String userName;
    private String imageUrl;
    private String gender;
    private String email;
    private Date DOB;
    private Long mobileNumber;


}
