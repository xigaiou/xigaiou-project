package com.xigaiou.xigaiouproject.common.gql;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xigaiou.xigaiouproject.common.gqlSupport.PageRows;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
public abstract class BaseFetchReq<T> extends BaseActionReq<T> implements FetchReq<T> {
    private PageRows<T> pageRows;
    private String orderBy;

    public BaseFetchReq(T input){
        this(input, new HashMap(0));
    }

    public BaseFetchReq(T input, Map<String, Object> map){
        super(input, map);
    }

}
