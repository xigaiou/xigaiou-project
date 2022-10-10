package com.xigaiou.xigaiouproject.domain.service;

import com.xigaiou.xigaiouproject.application.assembler.ArticleAssembler;
import com.xigaiou.xigaiouproject.common.GeneratorIdUtil;
import com.xigaiou.xigaiouproject.domain.entity.Article;
import com.xigaiou.xigaiouproject.domain.entity.ArticleRequest;
import com.xigaiou.xigaiouproject.domain.repository.ArticleRepository;
import com.xigaiou.xigaiouproject.infrastructure.persistence.ArticleRepositoryImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ArticleService {
    @Autowired
    private ArticleRepositoryImpl articleRepository;

    public List<Article> queryArticles(){
        return articleRepository.queryArticles();
    }
    public void addArticle(ArticleRequest articleRequest){
        Article article = ArticleAssembler.INST.convertToArticle(articleRequest);
        article.setId(new GeneratorIdUtil().nextId());
        article.setTime(new Date());
        articleRepository.addArticle(article);
    }
    public Article getArticleById(String id){
        return articleRepository.getArticleById(id);
    }
    public void deleteArticleById(String id){
        articleRepository.deleteArticleById(id);
    }
}
