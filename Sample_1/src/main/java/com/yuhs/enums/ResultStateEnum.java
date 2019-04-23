package com.yuhs.enums;

/**
 * 使用枚举表述常量数据字典
 */
public enum ResultStateEnum {

    SUCCESS(200, "OK"), NO_NUMBER(404, "指定资源不存在");

    private int state;

    private String stateInfo;

    private ResultStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static ResultStateEnum stateOf(int index) {
        for (ResultStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}