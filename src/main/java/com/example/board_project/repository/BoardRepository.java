package com.example.board_project.repository;

import com.example.board_project.entity.Board;
import com.example.board_project.entity.Category;
import net.bytebuddy.jar.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board,Integer> {
    Page<Board> findByTitleContaining(String searchTitle, Pageable pageable);

    Page<Board> findByCategoryIs(Category category, Pageable pageable);
}




