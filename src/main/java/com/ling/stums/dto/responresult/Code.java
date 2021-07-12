package com.ling.stums.dto.responresult;

/**
 * @Author Lingkongran
 * @Date 2020/11/28 0028 14:27
 * @Version 1.0
 */
public enum  Code {
    //全局状态码
    SUCCESS(10000,"操作成功"),
    ERROR(0,"操作失败");
    int code;
    String  msg;

    Code(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
