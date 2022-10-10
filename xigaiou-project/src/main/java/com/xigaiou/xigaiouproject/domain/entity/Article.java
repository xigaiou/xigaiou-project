package com.xigaiou.xigaiouproject.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 富文本编辑器
 */
@Data
@TableName("ARTICLE")
public class Article {
    @TableId
    private String id;
    private String author;
    private String title;
    private String content;
    private Date time;
}
