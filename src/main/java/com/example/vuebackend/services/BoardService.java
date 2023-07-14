package com.example.vuebackend.services;

import com.example.vuebackend.entity.BoardEntity;
import com.example.vuebackend.entity.BoardRepository;
import com.example.vuebackend.web.dtos.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public List<BoardDto> getBoardList() {
        List<BoardEntity> boardEntities = boardRepository.findAll();
        List<BoardDto> dtos = new ArrayList<>();

        for (BoardEntity entity : boardEntities) {
            BoardDto dto = BoardDto.builder()
                    .idx(entity.getIdx())
                    .type(entity.getType())
                    .email(entity.getEmail())
                    .password(entity.getPassword())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .author(entity.getAuthor())
                    .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .fileUrl(entity.getFileUrl())
                    .build();

            dtos.add(dto);
        }


        return dtos;
    }

    public BoardDto getBoard(Long id) {
        BoardEntity entity = boardRepository.findByIdWithFile(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));

        String fileUrl = null;
        if (entity.getFile() != null) {
            fileUrl = entity.getFileUrl();
        }

        log.debug("File URL: " + fileUrl);
        return BoardDto.builder()
                .idx(entity.getIdx())
                .type(entity.getType())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .title(entity.getTitle())
                .contents(entity.getContents())
                .author(entity.getAuthor())
                .createdAt(entity.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .fileUrl(entity.getFileUrl())
                .build();
    }

    public BoardEntity create(BoardDto boardDto, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = "./upload";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Path.of(uploadDir, fileName);


            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            boardDto.setFileUrl(fileName);
        } else {
            boardDto.setFileUrl(null); // 파일이 없는 경우에는 파일 URL을 null로 설정
        }

        BoardEntity entity = BoardEntity.builder()
                .type(boardDto.getType())
                .email(boardDto.getEmail())
                .password(boardDto.getPassword())
                .title(boardDto.getTitle())
                .contents(boardDto.getContents())
                .author(boardDto.getAuthor())
                .createdAt(LocalDateTime.now())
                .fileUrl(boardDto.getFileUrl())
                .build();

        return boardRepository.save(entity);
    }

    public BoardEntity update(BoardDto boardDto, MultipartFile file) throws IOException {
        if (file != null && !file.isEmpty()) {
            String uploadDir = "./upload";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path filePath = Path.of(uploadDir, fileName);

            // Create upload directory if it doesn't exist


            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            Files.createDirectories(filePath.getParent());
            boardDto.setFileName(file.getOriginalFilename());
            boardDto.setFileUrl(fileName);
        }  else {
            boardDto.setFileName(null); // 파일이 없는 경우에는 파일 이름과 URL을 null로 설정
            boardDto.setFileUrl(null);
        }

        BoardEntity entity = boardRepository.findById(boardDto.getIdx())
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        entity.setType(boardDto.getType());
        entity.setEmail(boardDto.getEmail());
        entity.setPassword(boardDto.getPassword());
        entity.setTitle(boardDto.getTitle());
        entity.setContents(boardDto.getContents());
        entity.setAuthor(boardDto.getAuthor());
        entity.setFileName(boardDto.getFileName());

        return boardRepository.save(entity);
    }

    public void delete(Long id) {
        BoardEntity entity = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("게시글을 찾을 수 없습니다."));
        boardRepository.delete(entity);
    }
}
