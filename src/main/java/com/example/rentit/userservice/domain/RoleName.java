package com.example.rentit.userservice.domain;

/**
 * @author Shimi Sadaka
 * @version 1.0
 * @since 3/10/2022
 */
public enum RoleName {

    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"),
    ;

    private String value;

    RoleName(String roleName) {
        this.value = roleName;
    }

    public String getValue() {
        return this.value;
    }
}
