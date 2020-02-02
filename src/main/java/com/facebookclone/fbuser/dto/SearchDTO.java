package com.facebookclone.fbuser.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


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
    private String DOB;
    private Long mobileNumber;
    private List<String> interests;
    private String profileType;
    private String displayType;
    private HashSet<String> friendIds;
    private HashSet<String> pendingFriendIds;


}
