package com.example.memberboard.model;

import java.time.LocalDateTime;

public class Board {

    private Long id;
    private String title;
    private String content;
    private Long member_id;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMember_id() {
        return member_id;
    }

    public void setMember_id(Long member_id) {
        this.member_id = member_id;
    }
}
