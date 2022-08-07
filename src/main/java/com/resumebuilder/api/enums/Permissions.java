package com.resumebuilder.api.enums;

public enum Permissions {
    RESUMES_READ("resumes:read"),
    RESUMES_WRITE("resumes:read");

    private final String permission;

    Permissions(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
