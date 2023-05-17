// 
// 
// 

package com.car.pojo;

import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.Serializable;

public class Page<T> implements Serializable
{
    private Integer index;
    private Integer currentPage;
    private Integer currentCount;
    private Integer totalCount;
    private Integer totalPage;
    private String sortName;
    private String sortOrder;
    private Map<String, Object> params;
    private String search;
    private List<T> list;
    
    public Page() {
        this.params = new HashMap<String, Object>();
        this.list = new ArrayList<T>();
    }
    
    public Map<String, Object> getParams() {
        return this.params;
    }
    
    public void setParams(final Map<String, Object> params) {
        this.params = params;
    }
    
    public Integer getIndex() {
        return this.index;
    }
    
    public void setIndex(final Integer index) {
        this.index = index;
    }
    
    public Integer getCurrentPage() {
        return this.currentPage;
    }
    
    public void setCurrentPage(final Integer currentPage) {
        this.currentPage = currentPage;
    }
    
    public Integer getCurrentCount() {
        return this.currentCount;
    }
    
    public void setCurrentCount(final Integer currentCount) {
        this.currentCount = currentCount;
    }
    
    public Integer getTotalCount() {
        return this.totalCount;
    }
    
    public void setTotalCount(final Integer totalCount) {
        this.totalCount = totalCount;
    }
    
    public Integer getTotalPage() {
        return this.totalPage;
    }
    
    public void setTotalPage(final Integer totalPage) {
        this.totalPage = totalPage;
    }
    
    public List<T> getList() {
        return this.list;
    }
    
    public void setList(final List<T> list) {
        this.list = list;
    }
    
    public String getSearch() {
        return this.search;
    }
    
    public void setSearch(final String search) {
        this.search = search;
    }
    
    public String getSortName() {
        return this.sortName;
    }
    
    public void setSortName(final String sortName) {
        this.sortName = sortName;
    }
    
    public String getSortOrder() {
        return this.sortOrder;
    }
    
    public void setSortOrder(final String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    public void sortDefault(final String sortName, final String sortOrder) {
        if (StringUtils.isEmpty((CharSequence)this.getSortName()) || StringUtils.isEmpty((CharSequence)this.getSortOrder())) {
            this.setSortName(sortName);
            this.setSortOrder(sortOrder);
        }
    }
}
