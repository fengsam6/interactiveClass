package com.code.classsystem.vo;

import com.code.classsystem.entity.Notice;

public class NoticeVo  extends Notice {
   private int page;
    private int limit;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
