package com.xigaiou.xigaiouproject.common.gql;

public interface GeneralActionReq<T> extends ActionReq<T>{
    String getNs();

    String getName();

    default String getDataId(){
        return BaseAction.concatDataId(this.getNs(), this.getName(), new String[0]);
    }

    /**
     * 其实没有用到GraphQL，默认的都是General
     * @return EngineTypeEnum.GENERAL
     */
    default EngineTypeEnum getEngineType(){
        return EngineTypeEnum.GENERAL;
    }
}
