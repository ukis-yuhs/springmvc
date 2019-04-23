package com.yuhs.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuhaisheng on 2019/4/22.
 */
public class ResponseJsonResult {
    // 返回结果状态值
    private int status = 200;
    // 返回结果信息
    private String msg;
    // 返回结果对象
    private Object obj;
    // 返回结果列表
    private List<?> list = new ArrayList<Object>();

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
