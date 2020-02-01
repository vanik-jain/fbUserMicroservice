package com.facebookclone.fbuser.response;

import lombok.Data;

@Data
public class TokenResponse {
    private Long id;
    private String name;
    private String email;
    private String role;
}