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

    @Transactional
    public void join(ReplySaveDto replySaveDto, User user) {

        Reply reply = Reply.builder()
                .content(replySaveDto.getContent())
                .user(user)
                .build();
        replyRepository.save(reply);
    }
    public List<Reply> takeAll() {
        List<Reply> all = replyRepository.findAll();
        return all;

    }
    public void deleteById(int replyId) {
        replyRepository.deleteById(replyId);
    }

    @Transactional
    public Reply modify(ReplyModifyDto replyModifyDto) {
        Reply reply = replyRepository.findById(replyModifyDto.getId()).get();
        reply.setContent(replyModifyDto.getContent());
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
}
