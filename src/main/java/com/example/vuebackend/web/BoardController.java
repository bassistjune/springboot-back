package com.example.vuebackend.web;

import com.example.vuebackend.entity.BoardEntity;
import com.example.vuebackend.services.BoardService;
import com.example.vuebackend.web.dtos.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins="https://bassistjune.github.io/")
@RestController
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/board/list")
    public List<BoardDto> boardList() {
        return boardService.getBoardList();
    }

    @GetMapping("/board/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping("/board")
    public BoardEntity create(@ModelAttribute BoardDto boardDto, @RequestParam(required = false) MultipartFile file) throws Exception {
        return boardService.create(boardDto, file);
    }

    @PatchMapping("/board/{id}")
    public BoardEntity update(@PathVariable Long id, @ModelAttribute BoardDto boardDto, @RequestParam(required = false) MultipartFile file) throws Exception {
        boardDto.setIdx(id);
        return boardService.update(boardDto, file);
    }

    @DeleteMapping("/board/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}
