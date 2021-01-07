package com.duyi.qxgl.domain;

import java.util.List;

public class Page {
    private Integer page;
    private Integer max;
    private List<?> userList;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMax() {
        return max;
    }

    public void setMax(Integer max) {
        this.max = max;
    }

    public List<?> getUserList() {
        return userList;
    }

    public void setUserList(List<?> userList) {
        this.userList = userList;
    }

    public Page() {
    }

    public Page(Integer page, Integer max, List<?> userList) {
        this.page = page;
        this.max = max;
        this.userList = userList;
    }
}
