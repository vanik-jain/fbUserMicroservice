package com.facebookclone.fbuser.collections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document
@Getter
@Setter
@ToString
public class BusinessUser
{
    @Id
   private String adminId;
   private List<String> moderatorIds;
   private String category;
   private String status;


}
