package com.ljs.callray.domain;

import java.util.List;

/**
 * @author administrator
 * @date 2021/5/27 16:10
 */
public class PageForm<T> {

    private Integer current;
    private Integer size;
     private Long total;
     private List<T> dataList;

    public PageForm() {
    }

    public PageForm(Integer current, Integer size, Long total, List<T> dataList) {
        this.current = current;
        this.size = size;
        this.total = total;
        this.dataList = dataList;
    }
    public  PageForm(Integer current, Integer size){
        this.current = current;
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}
