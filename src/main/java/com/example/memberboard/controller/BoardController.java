package com.example.memberboard.controller;

import java.util.List;

import com.example.memberboard.model.Member;
import com.example.memberboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.memberboard.model.Board;
import com.example.memberboard.service.BoardService;

import javax.servlet.http.HttpSession;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private MemberService memberService;

    @GetMapping("/board")
    public String boardList(Model model) {
        List<Board> boardList = boardService.getBoardList();
        model.addAttribute("boardList", boardList);
        return "board/list";
    }

    @GetMapping("/board/{id}")
    public String boardDetail(@PathVariable Long id, Model model) {
        Board board = boardService.getBoardById(id);
        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("/board/new")
    public String boardForm() {
        return "board/form";
    }

    @PostMapping("/board/new")
    public String createBoard(@RequestParam String title, @RequestParam String content, HttpSession session) {
        Board board = new Board(title, content);
        Member member = memberService.getMemberByUsername((String) session.getAttribute("username"));
        board.setMember_id(member.getId());
        boardService.createBoard(board);
        return "redirect:/board";
    }

    @GetMapping("/board/{id}/edit")
    public String boardEditForm(@PathVariable("id") String id, Model model) {
        Long boardId = Long.parseLong(id);
        Board board = boardService.getBoardById(boardId);
        model.addAttribute("board", board);
        return "board/edit";
    }

    @PostMapping("/board/{id}/edit")
    public String updateBoard(@PathVariable("id") String id, @RequestParam String title, @RequestParam String content) {
        Long boardId = Long.parseLong(id);
        Board board = boardService.getBoardById(boardId);
        board.setTitle(title);
        board.setContent(content);
        boardService.updateBoard(board);
        return "redirect:/board/" + boardId;
    }

    @GetMapping("/board/{id}/delete")
    public String deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return "redirect:/board";
    }
}
