package com.example.memberboard.controller;

import com.example.memberboard.model.Member;
import com.example.memberboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class RegistrationController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/register")
    public String registrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(Member member, Model model) {
        memberService.register(member);
        return "redirect:/login";
    }

}
