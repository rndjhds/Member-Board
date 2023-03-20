package com.example.memberboard.controller;

import com.example.memberboard.model.Member;
import com.example.memberboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password, HttpSession session,
                        Model model) {
        boolean memberMatching = memberService.authenticate(username, password);
        if (memberMatching == false) {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        } else {
            session.setAttribute("username", username);
            return "redirect:/board";
        }
    }

}
