package com.example.memberboard.dao;

import java.util.List;

import com.example.memberboard.model.Board;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {

    List<Board> findAll();

    Board findById(Long id);

    void create(Board board);

    void update(Board board);

    void delete(Long id);

}
