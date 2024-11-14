package com.application.petcare.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public enum Role {
    ROLE_ADMIN,
    ROLE_EMPLOYEE,
    ROLE_CUSTOMER
}
