package com.xigaiou.xigaiouproject.domain.repository;

import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;

import java.util.List;

public interface EmployeesInfoSceneConfRepository {
    List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode);
}
