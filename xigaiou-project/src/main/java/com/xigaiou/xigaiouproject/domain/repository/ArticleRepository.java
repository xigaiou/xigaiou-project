package com.xigaiou.xigaiouproject.domain.repository;

import com.xigaiou.xigaiouproject.domain.entity.Article;

import java.util.List;

public interface ArticleRepository {

    List<Article> queryArticles();
    void addArticle(Article article);
    Article getArticleById(String id);
    void deleteArticleById(String id);
}
