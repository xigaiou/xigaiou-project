package com.xigaiou.xigaiouproject.common.generalQuery;

import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;

import java.util.Map;

/**
 * 通用搜索员工信息接口
 * @author xigaiou
 * @time 2022-12-05
 */
public interface GeneralSearchEmployeesInfoInterface<T> {
    String getDataSrc();

    T fetchOne(EmployeesInfoSceneConf employeesInfoSceneConf, Map<String, Object> map);
}
