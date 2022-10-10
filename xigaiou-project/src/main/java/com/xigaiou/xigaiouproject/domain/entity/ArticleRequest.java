package com.xigaiou.xigaiouproject.domain.entity;

import lombok.Data;

@Data
public class ArticleRequest {
    private String author;
    private String title;
    private String content;
}
