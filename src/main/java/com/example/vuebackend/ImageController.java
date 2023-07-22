package com.example.vuebackend;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class ImageController {

    // 이미지를 업로드하고 저장하는 API 엔드포인트
    @PostMapping("upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            // 파일 저장 경로 설정
            String uploadFolderPath = "src/main/resources/upload/"; // resources 폴더 안의 upload 폴더 경로

            // 파일 이름 중복을 피하기 위해 UUID 사용
            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path uploadPath = Paths.get(uploadFolderPath + fileName);

            Files.createDirectories(uploadPath.getParent());
            Files.write(uploadPath, file.getBytes());

            // 이미지 URL 반환 (Vue.js에서 이미지를 불러올 때 사용할 URL)
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return "이미지 업로드에 실패했습니다.";
        }
    }
}
