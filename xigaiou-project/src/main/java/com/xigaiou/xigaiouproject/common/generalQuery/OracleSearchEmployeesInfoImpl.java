package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.common.gqlSupport.JdbcExportUtil;
import com.xigaiou.xigaiouproject.domain.entity.EmployeesInfoSceneConf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OracleSearchEmployeesInfoImpl implements GeneralSearchEmployeesInfoInterface<JSONObject>{
    /**
     * 数据源
     */
    private static final String DATA_SRC = "oracle";
    /**
     * dataSource
     */
    @Autowired
    private DataSource dataSource;

    public String getDataSrc(){
        return DATA_SRC;
    }

    public JSONObject fetchOne(EmployeesInfoSceneConf employeesInfoSceneConf, Map<String, Object> map){
        String qryTeplt = employeesInfoSceneConf.getQryTeplt();
        Map<String, JSONObject> resultMap = new HashMap<>();
        try{
            JdbcExportUtil.executeSelect(dataSource, 1, qryTeplt, new Object[0], (fieldNames, resultSet) -> {
                JSONObject row = new JSONObject(fieldNames.length);
                for(int i = 0; i < fieldNames.length; ++i){
                    Object value = resultSet.getObject(i + 1);
                    row.put(fieldNames[i], value);
                }
                resultMap.put("result", row);
                return 1;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultMap.get("result");
    }

    public List<JSONObject> fetchList(EmployeesInfoSceneConf employeesInfoSceneConf, Map<String, Object> map){
        String qryTeplt = employeesInfoSceneConf.getQryTeplt();
        List<JSONObject> resultList = new ArrayList<>();
        try{
            JdbcExportUtil.executeSelectBatch(dataSource, qryTeplt, new Object[0], (fieldNames, resultSet) -> {
                JSONObject row = new JSONObject(fieldNames.length);
                for(int i = 0; i < fieldNames.length; ++i){
                    Object value = resultSet.getObject(i + 1);
                    row.put(fieldNames[i], value);
                }
                resultList.add(row);
                return 1;
            });
        } catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }
}
