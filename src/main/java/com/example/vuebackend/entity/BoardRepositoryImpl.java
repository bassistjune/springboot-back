package com.example.vuebackend.entity;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // 이 어노테이션을 추가하여 스프링 빈으로 등록합니다.
public class BoardRepositoryImpl implements BoardRepository {
    private List<BoardEntity> boardEntities = new ArrayList<>();

    @Override
    public List<BoardEntity> findAll() {
        return boardEntities;
    }

    @Override
    public Optional<BoardEntity> findById(Long id) {
        return boardEntities.stream()
                .filter(board -> board.getIdx().equals(id))
                .findFirst();
    }

    @Override
    public BoardEntity save(BoardEntity boardEntity) {
        boardEntities.add(boardEntity);
        return boardEntity;
    }

    @Override
    public void delete(BoardEntity boardEntity) {
        boardEntities.remove(boardEntity);
    }
}
