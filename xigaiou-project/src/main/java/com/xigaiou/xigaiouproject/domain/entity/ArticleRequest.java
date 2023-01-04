package com.xigaiou.xigaiouproject.domain.entity;

import lombok.Data;

@Data
public class ArticleRequest {
    private String author;
    private String title;
    private String content;

    public ArticleRequest(String author, String title, String content){
         this.author = author;
         this.title = title;
         this.content = content;
    }
    public ArticleRequest(String author){
        this.author = author;
        this.title = "title";
        this.content = "content";
    }
}
