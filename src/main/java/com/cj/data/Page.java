package com.cj.data;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    private List data;
    private Long total;
    private int pageSize;
    private int totalPages;

    public Page() {
    }

    public List getData() {
        return this.data;
    }

    public void setData(List data) {
        this.data = data;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return this.totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
