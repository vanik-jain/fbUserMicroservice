package com.facebookclone.fbuser.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;


@Getter
@Setter
public class BusinessUserDTO
{
    private String adminId;
    private List<String> moderatorIds;
    private String category;
    private String status;
    private String businessName;
    private String businessImageUrl;
    private String email;
}
