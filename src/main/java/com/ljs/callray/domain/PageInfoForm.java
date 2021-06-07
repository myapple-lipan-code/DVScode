package com.ljs.callray.domain;

/**
 * @author administrator
 * @date 2021/6/1 9:51
 */
public class PageInfoForm {
    private Integer current;
    private Integer size;
    private String sortColumn;
    private String sortType;

    public PageInfoForm(){}
    public PageInfoForm(Integer current, Integer size) {
        this.current = current;
        this.size = size;
    }
    public PageInfoForm(Integer current, Integer size, String sortColumn, String sortType) {
        this.current = current;
        this.size = size;
        this.sortColumn = sortColumn;
        this.sortType = sortType;
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


    public String getSortColumn() {
        return sortColumn;
    }

    public void setSortColumn(String sortColumn) {
        this.sortColumn = sortColumn;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
