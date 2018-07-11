package com.example.pcs.fragmentcase.base;

import java.io.Serializable;

/**
 * @author pcs
 * @since 2018-07-02.
 */
public class BaseModel implements Serializable {
    private static final long serialVersionUID = 5268625605268545266L;

    private int type;//用于可能是多种布局

    private int code;

    private String msg;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
