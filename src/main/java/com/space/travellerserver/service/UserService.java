package com.space.travellerserver.service;

import com.space.travellerserver.dto.user.UserBasicInfoDto;
import com.space.travellerserver.entity.user.User;
import com.space.travellerserver.repositiory.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserBasicInfoDto getUserBasicInfo(String email) {

        User user = userRepository.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
        UserBasicInfoDto userBasicInfoDto = new UserBasicInfoDto();
        userBasicInfoDto.setId(user.getId());
        userBasicInfoDto.setUsername(user.getUsername());
        userBasicInfoDto.setRole(user.getRole());
        userBasicInfoDto.setFirstName(user.getFirstName());
        userBasicInfoDto.setLastName(user.getLastName());

        return userBasicInfoDto;
    }
}
