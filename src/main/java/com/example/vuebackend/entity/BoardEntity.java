package com.example.vuebackend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "board")
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    private String type;
    private String email;
    private String password;
    private String title;
    private String contents;
    private String author;
    private String fileName;
    @Column(name = "file_url")
    private String fileUrl;
    private LocalDateTime createdAt;
    @OneToOne(mappedBy = "board", cascade = CascadeType.ALL)
    private FileEntity file;

    // 추가: FileEntity 객체 반환하는 getFile() 메소드
    public FileEntity getFile() {
        return file;
    }

    // 추가: FileEntity 객체 설정하는 setFile() 메소드
    public void setFile(FileEntity file) {
        this.file = file;
    }
}
