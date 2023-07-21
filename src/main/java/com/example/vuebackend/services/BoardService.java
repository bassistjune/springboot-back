package com.example.vuebackend.services;

import com.example.vuebackend.entity.BoardEntity;
import com.example.vuebackend.entity.BoardRepository;
import com.example.vuebackend.web.dtos.BoardDto;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        // TODO: 데이터를 조회하여 BoardDto 리스트로 변환하는 로직 구현
        return null;
    }

    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        // TODO: 데이터를 BoardDto로 변환하는 로직 구현
        return null;
    }

    public BoardEntity create(BoardDto boardDto, MultipartFile file) throws IOException {
        // TODO: 게시물 생성 로직 및 파일 업로드 구현
        return null;
    }

    public BoardEntity update(BoardDto boardDto, MultipartFile file) throws IOException {
        // TODO: 게시물 수정 로직 및 파일 업로드 구현
        return null;
    }

    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}
