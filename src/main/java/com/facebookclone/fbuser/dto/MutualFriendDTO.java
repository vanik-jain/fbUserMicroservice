package com.facebookclone.fbuser.dto;

import com.facebookclone.fbuser.collections.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MutualFriendDTO {
    private List<User> userList;
    private Boolean isFriend;
}
