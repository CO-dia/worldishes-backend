package com.worldishes.dto;

public class UserDTO {
    private String name;
    private String profileImageUrl;
    private String googleId;

    public UserDTO(String name, String profileImageUrl, String googleId) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.googleId = googleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }
}
