package com.donaldy.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
public class Page<T> implements Serializable {
    private int page = 1;
    private int pageSize = 10;
    private int totalNum;
    private int totalPage;
    private List<T> list = Collections.emptyList();

    public Page(int page, int pageSize, int totalNum, List<T> list) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalNum = totalNum;
        this.list = list;
    }

    public Page() {
    }

    public Page(int total, List<T> list) {
        this.totalNum = total;
        this.list = list;
    }

}
