package com.xigaiou.xigaiouproject.common.gql;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;
import com.xigaiou.xigaiouproject.common.gqlSupport.ValidUtil;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import static com.xigaiou.xigaiouproject.common.gqlSupport.HttpUtil.convertRequestParamMap;

@NoArgsConstructor
public class ParamMapFetchReq extends DefaultGeneralFetchReq<Map<String, Object>>{
    public ParamMapFetchReq(String name, HttpServletRequest request){
        this("", name, request);
    }

    public ParamMapFetchReq(String ns, String name, HttpServletRequest request){
        this(ns, name, convertRequestParamMap(request.getParameterMap(), false));
    }

    public ParamMapFetchReq(String name, Map<String, Object> input){
        this("", name, input);
    }

    /**
     * 实际调用
     * @param ns ns
     * @param name name
     * @param input input
     */
    public ParamMapFetchReq(String ns, String name ,Map<String, Object> input){
        super(ns, name, input);
        String fl = (String)input.get("fl");
        if(!(StringUtils.isEmpty(fl) || ValidUtil.isValidMultiFieldId(fl))){
            throw new IllegalArgumentException("无效的参数：fl");
        }
        this.setFl((String)input.get("fl"));
        String pageNum = String.valueOf(input.get("pageNum"));
        String pageSize = String.valueOf(input.get("pageSize"));
        if(StringUtils.isNumeric(pageNum) || StringUtils.isNumeric(pageSize)){
            int nPageSize = StringUtils.isNumeric(pageSize) ? Integer.parseInt(pageSize) : 10;
            int nPageNum = StringUtils.isNumeric(pageNum) ? Integer.parseInt(pageNum) : 1;
            PageRows pageReq = new PageRows(Math.max(nPageNum, 1), Math.max(nPageSize, 0));
            pageReq.setTotal(-1L);
            //BaseFetchReq
            setPageRows(pageReq);
        }
        //BaseFetchReq
        setOrderBy((String)input.get("orderBy"));
    }
}
