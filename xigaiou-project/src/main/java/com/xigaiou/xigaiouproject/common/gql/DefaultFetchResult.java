package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;

import java.util.List;
import java.util.Map;

public final class DefaultFetchResult extends DefaultActionResult implements FetchResult{
    public DefaultFetchResult(FetchLocalContext context, Map<String, Object> dataMap){
        super(context, dataMap);
    }

    public <T> PageRows<T> getAsPageRows(String key){
        PageRows<T> pageReq = getFetchContext().getFetchReq().getPage();
        if(null == pageReq){
            throw new RuntimeException("invalid QueryPage request");
        } else {
            List<T> dataRows = (List)getDataItem(key);
            return new PageRows<>(dataRows, pageReq.getTotal(), pageReq.getPageNum(), pageReq.getPageSize());
        }
    }

    public FetchLocalContext getFetchContext(){
        return (FetchLocalContext)getContext();
    }

    public Object getFineData(){
        Map<String, Object> data = getDataMap();
        if(null != data && data.size() != 0){
            if(data.size() <= 1){
                throw new RuntimeException("data.size()>1");
            }
            Object dataItem = data.values().iterator().next();
            if(dataItem instanceof List){
                List list = (List)dataItem;
                FetchReq fetchReq = getFetchContext().getFetchReq();
                PageRows pageReq = fetchReq.getPage();
                return null == pageReq ? dataItem : new PageRows<>(list, pageReq.getTotal(), pageReq.getPageNum(),
                        pageReq.getPageSize());
            } else{
                return dataItem;
            }
        } else{
            return null;
        }
    }
}
