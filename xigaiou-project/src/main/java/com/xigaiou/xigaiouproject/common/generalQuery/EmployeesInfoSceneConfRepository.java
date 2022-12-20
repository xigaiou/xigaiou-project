package com.xigaiou.xigaiouproject.common.generalQuery;

import java.util.List;

public interface EmployeesInfoSceneConfRepository {
    List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode);
}
