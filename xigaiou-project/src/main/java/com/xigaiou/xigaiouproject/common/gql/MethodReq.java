package com.xigaiou.xigaiouproject.common.gql;

public interface MethodReq {
    String getMethod();

    Object getBody();

    default  <T> T getBodyAs(){
        T body = (T) getBody();
        if(null == body){
            System.out.println("method request body is null");
        }
        return body;
    }
}
