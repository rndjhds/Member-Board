package com.example.memberboard.service;

import java.util.List;

import com.example.memberboard.dao.BoardDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.memberboard.model.Board;

@Service
public class BoardService {

    @Autowired
    private BoardDao boardDao;

    public List<Board> getBoardList() {
        return boardDao.findAll();
    }

    public Board getBoardById(Long id) {
        return boardDao.findById(id);
    }

    public void createBoard(Board board) {
        boardDao.create(board);
    }

    public void updateBoard(Board board) {
        boardDao.update(board);
    }

    public void deleteBoard(Long id) {
        boardDao.delete(id);
    }

}
