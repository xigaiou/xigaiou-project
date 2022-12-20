package com.xigaiou.xigaiouproject.common.generalQuery;

import com.xigaiou.xigaiouproject.common.gql.BaseFetcher;
import com.xigaiou.xigaiouproject.common.gql.FetchEnv;
import com.xigaiou.xigaiouproject.common.gql.GeneralQueryFetchReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmployeesInfoSceneQueryFetcher extends BaseFetcher<List<EmployeesInfoSceneConf>> {
    /**
     * 场景配置表
     */
    @Autowired
    private EmployeesInfoSceneConfService employeesInfoSceneConfService;

    /**
     * 执行器（无缓存版）
     */
    public EmployeesInfoSceneQueryFetcher(){
        super("query", "employee-info-scene", "");
    }

    /**
     * 执行器（无缓存版）
     * @param fetchEnv fetcherEnv
     * @return List<EmployeesInfoSceneConf>
     */
    protected List<EmployeesInfoSceneConf> onExecute(FetchEnv fetchEnv){
        GeneralQueryFetchReq fetchReq = fetchEnv.getFetchReq();
        //原版为从缓存中拿，现在变成直接拿
        List<EmployeesInfoSceneConf> sceneConfList =
                employeesInfoSceneConfService.getSceneConfBySceneCode(fetchReq.getSceneCode());
        return sceneConfList;
    }

    /**
     * 替换模板里的查询条件
     * @param fetchEnv fetchEnv
     * @param sceneConfList sceneConfList
     * @return
     */
    protected List<EmployeesInfoSceneConf> handleResult(FetchEnv fetchEnv, List<EmployeesInfoSceneConf> sceneConfList){
        GeneralQueryFetchReq fetchReq = fetchEnv.getFetchReq();
        sceneConfList.stream().forEach(e -> {
            GeneralSearchEmployeesInfoInterface generalSearchEmployeesInfoImpl = QueryStrategyUtil.getInstance()
                    .getGeneralSearchEmployeesInfoMap().get(e.getDataSrc());
            //省去对查询模板的校验
        });
        return sceneConfList;
    }
}

