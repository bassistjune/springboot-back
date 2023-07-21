package com.example.vuebackend.web;

import com.example.vuebackend.entity.BoardEntity;
import com.example.vuebackend.services.BoardService;
import com.example.vuebackend.web.dtos.BoardDto;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/list")
    public List<BoardDto> getBoardList() {
        return boardService.getBoardList();
    }

    @GetMapping("/{id}")
    public BoardDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping("")
    public BoardEntity create(@ModelAttribute BoardDto boardDto, @RequestParam(required = false) MultipartFile file) throws Exception {
        return boardService.create(boardDto, file);
    }

    @PatchMapping("/{id}")
    public BoardEntity update(@PathVariable Long id, @ModelAttribute BoardDto boardDto, @RequestParam(required = false) MultipartFile file) throws Exception {
        boardDto.setIdx(id);
        return boardService.update(boardDto, file);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boardService.delete(id);
    }
}
