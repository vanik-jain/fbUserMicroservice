package com.facebookclone.fbuser.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewUserDataDto
{
   private String userId;
   private String userName;
   private String imageUrl;

}
