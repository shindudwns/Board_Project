package com.example.board_project.service;


import com.example.board_project.dto.BoardModifyDto;
import com.example.board_project.dto.BoardSaveDto;
import com.example.board_project.dto.BoardSelectDto;
import com.example.board_project.entity.Board;
import com.example.board_project.entity.Category;
import com.example.board_project.entity.User;
import com.example.board_project.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service

@Transactional
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    public void join(BoardSaveDto boardSaveDto, User user) {

        Board board = Board.builder()
                .title(boardSaveDto.getTitle())
                .content(boardSaveDto.getContent())
                .user(user)
                .category(boardSaveDto.getCategory())
                .build();
        boardRepository.save(board);
    }

    public Page<BoardSelectDto> takeAll(Pageable pageable) {
        Page<BoardSelectDto> boardSelectDtoPage = boardRepository.findAll(pageable).map(BoardSelectDto::boardToBoardSelectDto);
        return boardSelectDtoPage;

    }


    public void deleteById(int boardId) {
        boardRepository.deleteById(boardId);
    }


    public BoardSelectDto modify(BoardModifyDto boardModifyDto) {
        Board board = boardRepository.findById(boardModifyDto.getId()).get();
        board.setTitle(boardModifyDto.getTitle());
        board.setContent(boardModifyDto.getContent());
        board.setHit(board.getHit() - 1);
        board.setCategory(boardModifyDto.getCategory());
        boardRepository.save(board);
        BoardSelectDto boardSelectDto = BoardSelectDto.boardToBoardSelectDto(board);
        return boardSelectDto;
    }


    public BoardSelectDto detail(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        board.setHit(board.getHit() + 1);
        BoardSelectDto boardSelectDto = BoardSelectDto.boardToBoardSelectDto(board);
        return boardSelectDto;
    }

    public BoardSelectDto modifyForm(int boardId) {
        Board board = boardRepository.findById(boardId).get();
        BoardSelectDto boardSelectDto = BoardSelectDto.boardToBoardSelectDto(board);
        return boardSelectDto;
    }

    public Page<BoardSelectDto> searchBoard(String searchTitle, Pageable pageable) {
        //boardRepository의 findByTitleContaining함수로 Page<Board> 객체를 갖고오고
        // 이 객체를 BoardSelectDto의 boardToBoardSelectDto함수를 사용해서 매핑한 객체를 갖고와라
        //맵 함수 기억하자!
        Page<BoardSelectDto> boardSelectDtoPage = boardRepository.findByTitleContaining(searchTitle, pageable)
                .map(BoardSelectDto::boardToBoardSelectDto);

        return boardSelectDtoPage;
    }

    public List<BoardSelectDto> findAll() {
        List<Board> boardList = boardRepository.findAll();
        List<BoardSelectDto> boardSelectDtoList = new ArrayList<>();
        for (Board board : boardList) {
            boardSelectDtoList.add(BoardSelectDto.boardToBoardSelectDto(board));
        }
        return boardSelectDtoList;

    }

    public Page<BoardSelectDto> categoryBoard(Category category, Pageable pageable) {
        Page<BoardSelectDto> boardSelectDtoPage=null;
        if (category.getValue().equals("모두보기")) {
            boardSelectDtoPage = boardRepository.findAll(pageable)
                    .map(BoardSelectDto::boardToBoardSelectDto);
        } else {
            boardSelectDtoPage = boardRepository.findByCategoryIs(category, pageable)
                    .map(BoardSelectDto::boardToBoardSelectDto);
        }
        return boardSelectDtoPage;

    }
}
