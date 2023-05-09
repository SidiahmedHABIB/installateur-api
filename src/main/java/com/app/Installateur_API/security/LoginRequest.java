package com.app.Installateur_API.security;

public record LoginRequest(
        String grantType,
        String email,
        String password,
        boolean withRefreshToken,
        String refreshToken) { }
