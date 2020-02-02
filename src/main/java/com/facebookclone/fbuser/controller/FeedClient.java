package com.facebookclone.fbuser.controller;

import com.facebookclone.fbuser.dto.FeedDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "feed", url = "172.16.20.113:8084")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public interface FeedClient {

    @GetMapping("feed/createNewFeed")
    public String createNewFeed(@RequestBody FeedDTO feedDTO);
}
