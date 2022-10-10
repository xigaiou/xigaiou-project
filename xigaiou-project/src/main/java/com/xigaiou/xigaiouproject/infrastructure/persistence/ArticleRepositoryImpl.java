package com.xigaiou.xigaiouproject.infrastructure.persistence;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xigaiou.xigaiouproject.domain.entity.Article;
import com.xigaiou.xigaiouproject.domain.repository.ArticleRepository;
import com.xigaiou.xigaiouproject.infrastructure.mapper.ArticleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleRepositoryImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleRepository {
    public List<Article> queryArticles(){
        return baseMapper.queryArticles();
    }
    public void addArticle(Article article){
        baseMapper.addArticle(article);
    }
    public Article getArticleById(String id){
        return baseMapper.getArticleById(id);
    }
    public void deleteArticleById(String id){
        baseMapper.deleteArticleById(id);
    }
}
