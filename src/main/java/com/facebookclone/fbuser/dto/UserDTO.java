package com.facebookclone.fbuser.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;


@Getter
@Setter

public class UserDTO {

    private String userId;
    private String userName;
    private String imageUrl;
    private String gender;
    private String email;
    private String  DOB;
    private Long mobileNumber;
    private List<String> interests;
    private String profileType;
    private String displayType;
    private HashSet<String> friendIds;
    private HashSet<String> pendingFriendIds;


}
