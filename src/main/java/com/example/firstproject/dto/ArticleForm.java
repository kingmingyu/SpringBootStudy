package com.example.firstproject.dto;

import com.example.firstproject.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor // -> 생성자 모두 자동 생성
@Getter
@ToString // -> toString 메서드 모두 자동 생성
public class ArticleForm {
    private Long id; //수정 폼에서 사용할 id 필드 추가
    private String title; //제목을 받을 필드
    private String content; //내용을 받을 필드

    public Article toEntity() {
        return new Article(id, title, content); //null -> id로 변경
    }
}
