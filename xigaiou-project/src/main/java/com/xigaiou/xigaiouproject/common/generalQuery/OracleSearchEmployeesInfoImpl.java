package com.xigaiou.xigaiouproject.common.generalQuery;

import com.alibaba.fastjson.JSONObject;
import com.xigaiou.xigaiouproject.common.gqlSupport.JdbcExportUtil;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class OracleSearchEmployeesInfoImpl implements GeneralSearchEmployeesInfoInterface<JSONObject>{
    /**
     * 数据源
     */
    private static final String DATA_SRC = "oracle";
    /**
     * dataSource
     */
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
}
