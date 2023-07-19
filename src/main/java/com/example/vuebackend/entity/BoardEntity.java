package com.example.vuebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardEntity {
    private Long idx;
    private String type;
    private String email;
    private String password;
    private String title;
    private String contents;
    private String author;
    private String fileName;
    private String fileUrl;
    private LocalDateTime createdAt;
    private FileEntity file;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
