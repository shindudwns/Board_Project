package com.example.board_project.service;


import com.example.board_project.dto.BoardModifyDto;
import com.example.board_project.dto.BoardSaveDto;
import com.example.board_project.dto.ReplyModifyDto;
import com.example.board_project.dto.ReplySaveDto;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.Reply;
import com.example.board_project.entity.User;
import com.example.board_project.repository.BoardRepository;
import com.example.board_project.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public Reply join(ReplySaveDto replySaveDto, User user) {
        Board board = boardRepository.findById(replySaveDto.getBoardId()).get();
        board.setHit(board.getHit()-1);
        Reply reply = Reply.builder()
                .content(replySaveDto.getContent())
                .user(user)
                .board(board)
                .build();
        replyRepository.save(reply);
        return reply;
    }
//    public List<Reply> takeAll() {
//        List<Reply> replyList = replyRepository.findAll();
//        return replyList;
//    }
    @Transactional
    public void deleteById(int replyId) {
        replyRepository.deleteById(replyId);
    }

    @Transactional
    public Reply modify(ReplyModifyDto replyModifyDto) {
        Reply reply = replyRepository.findById(replyModifyDto.getReplyId()).get();
        replyRepository.save(reply);
        return reply;
    }

//    @Transactional
//    public Reply detail(int replyId) {
//        Reply reply = replyRepository.findById(replyId).get();
//        return reply;
//    }

    public Reply modifyForm(int replyId) {
        Reply reply = replyRepository.findById(replyId).get();
        return reply;
    }
    @Transactional
    public List<Reply> findByBoardId(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        List<Reply> replyList = replyRepository.findByBoardId(boardId);
        return replyList;
    }
}
