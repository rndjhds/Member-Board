package com.example.memberboard.kakaoauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class KakaoAuthServiceImpl implements KakaoAuthService {

    private static final String AUTHORIZATION_SERVER_URL = "https://kauth.kakao.com/oauth/authorize";
    private static final String TOKEN_SERVER_URL = "https://kauth.kakao.com/oauth/token";
    private static final String USERINFO_SERVER_URL = "https://kapi.kakao.com/v2/user/me";

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${kakao.client-id}")
    private String clientId;

    @Value("${kakao.client-secret}")
    private String clientSecret;

    public KakaoAuthServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getAuthorizationUrl(String redirectUri) {
        Map<String, String> params = new HashMap<>();
        params.put("client_id", clientId);
        params.put("redirect_uri", redirectUri);
        params.put("response_type", "code");

        return AUTHORIZATION_SERVER_URL + "?" + buildQueryString(params);
    }

    @Override
    public String getAccessToken(String code, String redirectUri) {
        Map<String, String> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("client_id", clientId);
        params.put("client_secret", clientSecret);
        params.put("redirect_uri", redirectUri);
        params.put("code", code);

        Map<String, String> response = restTemplate.postForObject(TOKEN_SERVER_URL, buildQueryString(params), Map.class);

        return response.get("access_token");
    }

    @Override
    public KakaoUserInfo getUserInfo(String accessToken) {
        String response = restTemplate.getForObject(USERINFO_SERVER_URL, String.class,
                "Bearer " + accessToken);

        try {
            return objectMapper.readValue(response,
                    KakaoUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("Error parsing Kakao user info", e);
        }
    }

    private String buildQueryString(Map<String, String> params) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }

            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }

        return sb.toString();
    }
}
