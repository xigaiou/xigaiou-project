package com.xigaiou.xigaiouproject.application.api;

import com.xigaiou.xigaiouproject.application.service.ArticleAppService;
import com.xigaiou.xigaiouproject.domain.entity.Article;
import com.xigaiou.xigaiouproject.domain.entity.ArticleRequest;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 富文本编辑器
 * @author 吴子敬
 * @date 2022-08-24
 */
@RestController
@Slf4j
@RequestMapping("/Article")
public class ArticleApi {
    /**
     * articleAppService
     */
    @Autowired
    private ArticleAppService articleAppService;

    /**
     * 查询所有的文章
     * @return List<Article>
     */
    @ApiOperation(value = "label", notes = "查询所有的文章")
    @GetMapping("/getAll")
    public List<Article> queryArticles(){
        return articleAppService.queryArticles();
    }

    /**
     * 增加一个文章
     * @param articleRequest articleRequest
     * @return String
     */
    @ApiOperation(value = "label", notes = "增加一个文章")
    @PostMapping("/add")
    public String addArticle(@RequestBody ArticleRequest articleRequest){
        articleAppService.addArticle(articleRequest);
        return "insert success";
    }

    /**
     * 根据id查看某文章
     * @param id id
     * @return List<Article>
     */
    @ApiOperation(value = "label", notes = "根据id查看某文章")
    @GetMapping("/getById")
    public Article getArticleById(String id){
        return articleAppService.getArticleById(id);
    }

    /**
     * 根据id删除某文章
     * @param id id
     */
    @ApiOperation(value = "label", notes = "根据id删除某文章")
    @GetMapping("/deleteById")
    public String deleteArticleById(String id){
        articleAppService.deleteArticleById(id);
        return "delete success";
    }
}
