package com.application.petcare.dto.user;

import com.application.petcare.enums.Role;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfosResponse {
    private String name;
    private String userImage;
    private String email;
    private Role role;
}
