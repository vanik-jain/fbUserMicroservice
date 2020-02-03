package com.facebookclone.fbuser.controller;


import com.facebookclone.fbuser.collections.BusinessUser;
import com.facebookclone.fbuser.collections.User;
import com.facebookclone.fbuser.dto.BusinessUserDTO;
import com.facebookclone.fbuser.dto.TokenRequest;
import com.facebookclone.fbuser.dto.UserDTO;
import com.facebookclone.fbuser.dto.FriendRequestDTO;
import com.facebookclone.fbuser.response.BaseResponse;
import com.facebookclone.fbuser.response.TokenResponse;
import com.facebookclone.fbuser.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/user")

public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginClient loginClient;

    @PostMapping("/editDetails")
    BaseResponse<UserDTO> editDetails(@RequestBody UserDTO userDTO, @RequestHeader HttpHeaders httpHeaders) {
        User user = new User();
        String authToken = Objects.requireNonNull(httpHeaders.get("Auth")).get(0);

        Long providerId = Long.valueOf(1);
        TokenRequest tokenRequest = new TokenRequest(providerId);
        TokenResponse tokenResponse = loginClient.getUserDetails(authToken, tokenRequest);
        Long userId = tokenResponse.getId();
        userDTO.setUserId(userId.toString());
        BeanUtils.copyProperties(userDTO, user);
        UserDTO userDTO1 = userService.saveUser(user);
        return new BaseResponse<>(true, null, userDTO1, HttpStatus.CREATED);

    }

    @PostMapping("/sendFriendRequest")
    ResponseEntity<BaseResponse> sendFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO, @RequestHeader HttpHeaders httpHeaders) {

        userService.sendFriendRequest(friendRequestDTO);
        BaseResponse baseResponse = new BaseResponse();
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }


    @PostMapping("/acceptFriendRequest")
    ResponseEntity<BaseResponse> acceptFriendRequest(@RequestBody FriendRequestDTO friendRequestDTO, @RequestHeader HttpHeaders httpHeaders) {

        userService.acceptFriendRequest(friendRequestDTO);
        BaseResponse baseResponse = new BaseResponse();
        return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
    }


    @GetMapping("/getUserDetails/{userId}")
    BaseResponse<User> getUserDetailsById(@PathVariable(value = "userId") String userId) {

        User user = userService.findUserById(userId);
        return new BaseResponse<>(true, null, user, HttpStatus.CREATED);
    }

    @GetMapping("/getFriends/{userId}")
    BaseResponse<List<User>> getFriendsList(@PathVariable(value = "userId") String userId) {
        List<User> friendsList = userService.getFriendList(userId);
        return new BaseResponse<>(true, null, friendsList, HttpStatus.CREATED);
    }


    @PostMapping("/getMutualFriends")
    BaseResponse<List<User>> getMutualFriends(@RequestBody FriendRequestDTO friendRequestDTO) {
        List<User> mutualFriendsList = userService.getMutualFriends(friendRequestDTO);
        return new BaseResponse<>(true, null, mutualFriendsList, HttpStatus.CREATED);

    }


    @PostMapping("/addBusinessDetails")
    BaseResponse<BusinessUserDTO>  addBusinessDetails(@RequestBody BusinessUser businessUser, @RequestHeader HttpHeaders httpHeaders)
    {
        BusinessUserDTO businessUserDTO = userService.saveBusinessUser(businessUser);
        return new BaseResponse<>(true, "null", businessUserDTO, HttpStatus.CREATED);
    }


    @GetMapping("/getBusinessUserDetails/{adminId}")
    BaseResponse<BusinessUserDTO> getBusinessUserDetails(@PathVariable String adminId)
    {   BusinessUserDTO businessUserDTO=new BusinessUserDTO();
        BusinessUser businessUser=userService.findBusinessUserById(adminId);
        BeanUtils.copyProperties(businessUser,businessUserDTO);
        return new BaseResponse<>(true,"null",businessUserDTO,HttpStatus.CREATED);

    }

}
