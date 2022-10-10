package com.xigaiou.xigaiouproject.application.service;

import com.xigaiou.xigaiouproject.domain.entity.Article;
import com.xigaiou.xigaiouproject.domain.entity.ArticleRequest;
import com.xigaiou.xigaiouproject.domain.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ArticleAppService {
    @Autowired
    private ArticleService articleService;

    public List<Article> queryArticles(){
        return articleService.queryArticles();
    }
    public void addArticle(ArticleRequest articleRequest){
        articleService.addArticle(articleRequest);
    }
    public Article getArticleById(String id){
        return articleService.getArticleById(id);
    }
    public void deleteArticleById(String id){
        articleService.deleteArticleById(id);
    }
}
