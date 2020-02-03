package com.facebookclone.fbuser.collections;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User
{
  @Id
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
  private HashSet<String> friendIds=null;
  private HashSet<String> pendingFriendIds;



}
