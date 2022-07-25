package io.github.bootsrc.cloud.api.auth.dto;

import java.io.Serializable;

public class LoginResult implements Serializable {
    private static final long serialVersionUID = -6237303722540302866L;

    private String accessToken;
    private String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
