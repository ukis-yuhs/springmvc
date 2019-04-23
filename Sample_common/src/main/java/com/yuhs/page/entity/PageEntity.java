package com.yuhs.page.entity;

import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/23.
 */
public class PageEntity {

    private Integer total;
    //详细信息
    private List<?> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }
}
