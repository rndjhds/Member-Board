package com.example.memberboard.kakaoauth;

import com.example.memberboard.kakaoauth.KakaoAuthService;
import com.example.memberboard.kakaoauth.KakaoUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KakaoAuthController {

    @Autowired
    private KakaoAuthService kakaoAuthService;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    @GetMapping("/kakao/login")
    public String kakaoLogin() {
        return "redirect:" + kakaoAuthService.getAuthorizationUrl(redirectUri);
    }

    @GetMapping("/kakao/callback")
    public String kakaoCallback(@RequestParam String code, Model model) {
        String accessToken = kakaoAuthService.getAccessToken(code, redirectUri);

        KakaoUserInfo userInfo = kakaoAuthService.getUserInfo(accessToken);

        model.addAttribute("userInfo", userInfo);

        return "/board/list";
    }
}
