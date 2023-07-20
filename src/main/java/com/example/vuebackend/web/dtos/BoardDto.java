package com.example.vuebackend.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDto implements Serializable {
    private Long idx;
    private String type;
    private String email;
    private String password;
    private String title;
    private String contents;
    private String author;
    private String createdAt;
    private String fileName;
    private String fileUrl;
    public Long getId() {
        return idx;
    }
}
