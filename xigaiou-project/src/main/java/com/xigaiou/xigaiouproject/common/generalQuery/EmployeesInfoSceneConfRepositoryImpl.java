package com.xigaiou.xigaiouproject.common.generalQuery;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class EmployeesInfoSceneConfRepositoryImpl extends
        ServiceImpl<EmployeesInfoSceneConfMapper, EmployeesInfoSceneConf> implements EmployeesInfoSceneConfRepository {
    private final String FIELD_SCENE_CODE = "SCENE_CODE";

    public List<EmployeesInfoSceneConf> getSceneConfBySceneCode(String sceneCode){
        QueryWrapper<EmployeesInfoSceneConf> wrapper = new QueryWrapper<>();
        wrapper.eq(FIELD_SCENE_CODE, sceneCode);
        List<EmployeesInfoSceneConf> list = baseMapper.selectList(wrapper);
        return list;
    }
}
