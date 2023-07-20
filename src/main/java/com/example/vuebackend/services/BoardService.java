package com.example.vuebackend.services;

import com.example.vuebackend.entity.BoardEntity;
import com.example.vuebackend.entity.BoardRepository;
import com.example.vuebackend.web.dtos.BoardDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {
    private final BoardRepository boardRepository;
    @Autowired // 추가
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        return boardEntities.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        return convertToDto(entity);
    }

    public BoardEntity create(BoardDto boardDto, MultipartFile file) throws IOException {
        // TODO: 게시물 생성 로직 및 파일 업로드 구현
        // 예시로 더미 데이터로 게시물 생성
        BoardEntity newBoard = new BoardEntity();
        newBoard.setTitle(boardDto.getTitle());
        newBoard.setContents(boardDto.getContents());
        return boardRepository.save(newBoard);
    }

    public BoardEntity update(BoardDto boardDto, MultipartFile file) throws IOException {
        // TODO: 게시물 수정 로직 및 파일 업로드 구현
        // 예시로 더미 데이터로 게시물 수정
        BoardEntity existingBoard = boardRepository.findById(boardDto.getId())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        existingBoard.setTitle(boardDto.getTitle());
        existingBoard.setContents(boardDto.getContents());
        return boardRepository.save(existingBoard);
    }

    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }

    private BoardDto convertToDto(BoardEntity boardEntity) {
        // TODO: BoardEntity를 BoardDto로 변환하는 로직 구현
        BoardDto boardDto = new BoardDto();
        boardDto.setIdx(boardEntity.getIdx());
        boardDto.setType(boardEntity.getType());
        boardDto.setEmail(boardEntity.getEmail());
        boardDto.setPassword(boardEntity.getPassword());
        boardDto.setTitle(boardEntity.getTitle());
        boardDto.setContents(boardEntity.getContents());
        boardDto.setAuthor(boardEntity.getAuthor());
        // 이 외 필요한 필드들을 변환해줍니다.
        return boardDto;
    }
}
