package com.xigaiou.xigaiouproject.common.gql;

import com.xigaiou.xigaiouproject.common.gqlSupport.HttpUtil;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
@NoArgsConstructor
public class ParamMapActionReq extends DefaultGeneralActionReq<Map<String, Object>>{
    public ParamMapActionReq(String ns, String name, HttpServletRequest request){
        this(ns, name, HttpUtil.convertRequestParamMap(request.getParameterMap(), false));
    }

    public ParamMapActionReq(String ns, String name, Map<String, Object> input){
        super(ns, name, input);
    }
}
