package com.xigaiou.xigaiouproject.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@TableName("EMPLOYEES_INFO_SCENE_CONF")
public class EmployeesInfoSceneConf{

    /**
     * 场景主键Id
     */
    @TableId
    private String pkId;

    /**
     * 查询场景Id
     */
    private String sceneCode;

    /**
     * 查询场景名字
     */
    private String sceneName;

    /**
     * 查询模板
     */
    private String qryTeplt;

    /**
     * 数据来源
     */
    private String dataSrc;

    /**
     * 数据源详情(表名)
     */
    private String dataSrcDtl;

    /**
     * 版本号
     */
    private String verNo;

    /**
     * 版本更新详情
     */
    private String versionUpDesc;

    /**
     * 单词返回数量限制
     */
    private int backCntLimit;

    /**
     * 创建人
     */
    private String crtBy;

    /**
     * 创建时间
     */
    private Date crtTime;
}
