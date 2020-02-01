package com.facebookclone.fbuser.service;

import com.facebookclone.fbuser.collections.BusinessUser;
import com.facebookclone.fbuser.collections.User;
import com.facebookclone.fbuser.dto.BusinessUserDTO;
import com.facebookclone.fbuser.dto.FriendRequestDTO;
import com.facebookclone.fbuser.dto.UserDTO;

import java.util.HashSet;
import java.util.List;

public interface UserService
{

    UserDTO saveUser(User user);
    BusinessUserDTO saveBusinessUser(BusinessUser businessUser);
    void sendFriendRequest(FriendRequestDTO friendRequestDTO);
    void acceptFriendRequest(FriendRequestDTO friendRequestDTO);
    List<User>getFriendList(String userId);
    User findUserById(String userId);
    BusinessUser findBusinessUserById(String BusinessUserId);
    List<User>getMutualFriends(FriendRequestDTO friendRequestDTO);


}
