package com.space.travellerserver.dto.user;

import com.space.travellerserver.entity.user.Role;
import lombok.Data;

@Data
public class UserBasicInfoDto {
    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;
}
