package com.facebookclone.fbuser.controller;

import com.facebookclone.fbuser.dto.TokenRequest;
import com.facebookclone.fbuser.response.TokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "jwt", url = "172.16.20.32:8080/jwt")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public interface LoginClient {
    @PostMapping("/getUserDetails")
    TokenResponse getUserDetails(@RequestHeader("Authorization") String accessToken, @RequestBody TokenRequest tokenRequest);
}