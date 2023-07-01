package com.example.board_project.service;


import com.example.board_project.dto.BoardModifyDto;
import com.example.board_project.dto.BoardSaveDto;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.User;
import com.example.board_project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service


public class BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void join(BoardSaveDto boardSaveDto, User user) {

        Board board = Board.builder()
                .title(boardSaveDto.getTitle())
                .content(boardSaveDto.getContent())
                .user(user)
                .build();
        boardRepository.save(board);
    }

    public List<Board> takeAll() {
        List<Board> all = boardRepository.findAll();
        return all;

    }


    public void deleteById(int boardId) {
        boardRepository.deleteById(boardId);
    }

    @Transactional
    public Board modify(BoardModifyDto boardModifyDto) {
        Board board = boardRepository.findById(boardModifyDto.getId()).get();
        board.setTitle(boardModifyDto.getTitle());
        board.setContent(boardModifyDto.getContent());
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public Board detail(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.setHit(board.getHit()+1);
        return board;
    }

    public Board modifyForm(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        return board;
    }
}
