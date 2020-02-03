package com.facebookclone.fbuser.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BusinessUser {
    @Id
    private String adminId;
    private List<String> moderatorIds;
    private String category;
    private String status;
    private String businessName;
    private String businessImageUrl;
    private String email;

}
