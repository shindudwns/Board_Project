package com.example.board_project.repository;


import com.example.board_project.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {


    List<Reply> findByBoardId(int boardId);
}
