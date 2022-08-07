package com.resumebuilder.api.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Roles {
    USER(Arrays.asList(Permissions.RESUMES_READ,Permissions.RESUMES_WRITE)),
    ADMIN(Arrays.asList(Permissions.RESUMES_READ,Permissions.RESUMES_WRITE));

    private final List<Permissions> permissions;

    Roles(List<Permissions> permissions) {
        this.permissions = permissions;
    }

    public List<Permissions> getPermissions() {
        return permissions;
    }

    public List<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream().map(permissions1 -> new SimpleGrantedAuthority(permissions1.getPermission()))
                .collect(Collectors.toList());
    }
}
