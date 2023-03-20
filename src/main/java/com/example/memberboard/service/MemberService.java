package com.example.memberboard.service;

import com.example.memberboard.dao.MemberDao;
import com.example.memberboard.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    @Autowired
    private MemberDao memberDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Member getMemberByUsername(String username) {
        return memberDao.findByUsername(username);
    }

    public void register(Member member) {
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        memberDao.insert(member);
    }

    public boolean authenticate(String username, String password) {
        Member member = memberDao.findByUsername(username);
        if (member == null) {
            return false;
        }
        return passwordEncoder.matches(password, member.getPassword());
    }

}
