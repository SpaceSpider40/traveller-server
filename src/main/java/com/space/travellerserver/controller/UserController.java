package com.space.travellerserver.controller;

import com.space.travellerserver.dto.user.UserBasicInfoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.space.travellerserver.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserBasicInfoDto> getBasicUserInfo(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserBasicInfo(email));
    }
}
