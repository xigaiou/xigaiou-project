package com.xigaiou.xigaiouproject.common.gqlSupport;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

/**
 * 自己定义的分页类
 * @author xigaiou
 * @param <T> 实体
 */
@Data
@NoArgsConstructor
public class PageRows<T> {
    private List<T> list;
    private long total;
    private int pageNum;
    private int pageSize;

    public PageRows(List<T> list, long total){
        this.list = list;
        this.total = total;
    }

    public PageRows(List<T> list){
        this(list, (long)list.size());
    }

    public PageRows(int pageNum, int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageRows(long total, int pageNum, int pageSize){
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public PageRows(List<T> list, long total, int pageNum, int pageSize){
        this.list = list;
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public String toString(){
        return "PageRows(list=" + getList() +
                ", total=" + getTotal() +
                ", pageNum" + getPageNum() +
                ", pageSize" + getPageSize();
    }
}
