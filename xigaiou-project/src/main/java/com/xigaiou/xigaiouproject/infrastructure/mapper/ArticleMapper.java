package com.xigaiou.xigaiouproject.infrastructure.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xigaiou.xigaiouproject.domain.entity.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    /**
     * 查询所有的文章
     * @return List<Article>
     */
    List<Article> queryArticles();

    /**
     * 新增一个文章
     * @param article article
     * @return String
     */
    void addArticle(Article article);

    /**
     * 根据文章id查询文章
     * @param id id
     * @return Article
     */
    Article getArticleById(String id);

    /**
     * 根据文章id删除文章
     * @param id id
     * @return String
     */
    void deleteArticleById(String id);
}
