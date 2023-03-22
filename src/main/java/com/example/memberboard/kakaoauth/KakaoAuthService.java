package com.example.memberboard.kakaoauth;

public interface KakaoAuthService {
    String getAuthorizationUrl(String redirectUri);
    String getAccessToken(String code, String redirectUri);
    KakaoUserInfo getUserInfo(String accessToken);
}