package com.uec.ibrain.ibrain.tenant.bean;

/**
 * <br/>Description:
 * <p>Author:ygao/高远</p>
 *
 * @param
 * @return
 * @throws Exception
 */
public class TenantParam {
    private String queryStr;

    private Integer pageStart;

    private Integer pageSize;

    public String getQueryStr() {
        return queryStr;
    }

    public void setQueryStr(String queryStr) {
        this.queryStr = queryStr;
    }

    public Integer getPageStart() {
        return pageStart;
    }

    public void setPageStart(Integer pageStart) {
        this.pageStart = pageStart;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }



}
