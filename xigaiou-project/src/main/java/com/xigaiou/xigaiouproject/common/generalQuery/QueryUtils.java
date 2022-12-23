package com.xigaiou.xigaiouproject.common.generalQuery;

import org.thymeleaf.util.StringUtils;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 查询模板替换
 * @author 西盖欧
 * @date 2022-12-21
 */
public class QueryUtils {
    /**
     *  \$ 代表匹配$
     *  \w+ 字符串
     *  ()? 可选
     *  \| 或
     *  [^ ] 里面的字符一个都没有
     *  : 和 任意空字符 和 [
     */
    private static final Pattern REGEX_VAR_NAME = Pattern.compile("\\$\\w+(\\|[^:\\s\\[]+)?");

    /**
     * 这个用不到，是用于匹配客户列表筛选项的{AND CUST_ID:$custId}?
     */
    private static final Pattern REGEX_OPTION_SEG = Pattern.compile("\\{([^?]+)}\\?");

    public static String parseQuery(String sql, Map<String, Object> paramMap, boolean setDefaultValue){
        String parseSegSql = parseSeg(sql, paramMap);

        StringBuffer resultSql = new StringBuffer(parseSegSql.length());
        Matcher mName = REGEX_VAR_NAME.matcher(parseSegSql);
        while(mName.find()){
            VarItem varItem = parseVarItem(mName.group(), paramMap);
            Object varVal = varItem.varVal;
            if(null == varVal || "".equals(varVal)){
                if(setDefaultValue){
                    varVal = varItem.defaultVal;
                } else{
                    continue;
                }
            }
            mName.appendReplacement(resultSql, String.valueOf(varVal));
        }
        mName.appendTail(resultSql);
        return resultSql.toString();
    }

    /**
     * 参数拼接
     * @param sql sql
     * @param paramMap paramMap
     * @return resultSql
     */
    public static String parseSeg(String sql, Map<String, Object> paramMap){
        StringBuilder resultSql = new StringBuilder(sql.length());
        Matcher m = REGEX_OPTION_SEG.matcher(sql);
        int begin = 0;
        while(m.find()){
            //参数前的部分
            resultSql.append(sql, begin, m.start());
            begin = m.end();
            //第一个括号里的东西
            String seg = m.group(1);

            if(paramMap.isEmpty()){
                continue;
            }

            Matcher mName = REGEX_VAR_NAME.matcher(seg);
            boolean exitsVar = true;
            while(mName.find()){
                VarItem varItem = parseVarItem(mName.group(), paramMap);
                if(null == varItem.varVal || StringUtils.isEmpty(varItem.varVal.toString())){
                    exitsVar = false;
                    break;
                }
            }
            if(exitsVar){
                resultSql.append(parseQuery(seg, paramMap, true));
            }
        }
        resultSql.append(sql.substring(begin));
        return resultSql.toString();
    }

    /**
     * 参数类
     */
    private static class VarItem{
        private String varName;
        private Object varVal;
        private String defaultVal;
    }

    /**
     * 参数拼接的实现函数
     * @param groupVarName groupVarName
     * @param varMap varMap
     * @return varItem
     */
    private static VarItem parseVarItem(String groupVarName, Map<String, Object> varMap){
        VarItem varItem = new VarItem();
        int loc = groupVarName.lastIndexOf("|");
        if(loc > 0){
            varItem.varName = groupVarName.substring(1, loc);
            String defaultValue = groupVarName.substring(loc+1);
            if(defaultValue.contains("$")){
                defaultValue = parseQuery(defaultValue, varMap, true);
            }
            varItem.defaultVal = defaultValue;
        } else{
            varItem.varName = groupVarName.substring(1);
            varItem.defaultVal = "*";
        }
        varItem.varVal = varMap.get(varItem.varName);
        return varItem;
    }
}
