package com.example.vuebackend.entity;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardRepository {
    List<BoardEntity> findAll();
    Optional<BoardEntity> findById(Long id);
    BoardEntity save(BoardEntity boardEntity);
    void delete(BoardEntity boardEntity);
}
