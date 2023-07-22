package com.example.vuebackend.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 기존의 findByIdWithFile 메서드는 이미지 업로드를 위한 필요가 없으므로 그대로 유지합니다.
    @Query("SELECT b FROM BoardEntity b LEFT JOIN FETCH b.file WHERE b.idx = :id")
    Optional<BoardEntity> findByIdWithFile(@Param("id") Long id);

    // 추가로 필요한 메서드들을 정의할 수 있습니다. (예: 특정 유저(email)가 작성한 게시물을 가져오는 메서드)
    // 필요에 따라서 원하는 메서드를 추가하시면 됩니다.
}
