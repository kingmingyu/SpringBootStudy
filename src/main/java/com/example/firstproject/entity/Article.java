package com.example.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // 엔티티 선언
@AllArgsConstructor
@NoArgsConstructor//기본 생성자 추가 어노테이션
@ToString
@Getter
public class Article {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성 기능 추가(숫자가 자동으로 매겨진다)
    private Long id;
    @Column
    private String title; //title 필드 선언 -> DB테이블의 title열과 연결
    @Column
    private String content; //content 필드 선언 -> DB 테이블의 content열과 연결

    public void patch(Article article) {
        if(article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}
