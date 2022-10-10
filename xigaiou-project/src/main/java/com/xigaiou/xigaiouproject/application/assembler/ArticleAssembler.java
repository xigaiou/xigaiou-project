package com.xigaiou.xigaiouproject.application.assembler;

import com.xigaiou.xigaiouproject.domain.entity.Article;
import com.xigaiou.xigaiouproject.domain.entity.ArticleRequest;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleAssembler {
    /**
     * INST
     */
    ArticleAssembler INST = Mappers.getMapper(ArticleAssembler.class);

    Article convertToArticle(ArticleRequest articleRequest);
}
