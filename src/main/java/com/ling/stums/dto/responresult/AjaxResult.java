package com.ling.stums.dto.responresult;

import java.io.Serializable;

public class AjaxResult<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer code;
    private String msg;
    private T data;

    public AjaxResult(Code code, T data) {
        this.code = code.getCode();
        this.msg = code.msg;
        this.data = data;
    }
    public AjaxResult(Code code) {
        this.code = code.getCode();
        this.msg = code.msg;
    }
    public AjaxResult(Code code,String msg) {
        this.code = code.getCode();
        this.msg =msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
