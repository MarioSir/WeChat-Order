package com.han.result;

import lombok.Data;

@Data
public class CodeMsg {

    private Integer code;
    private String msg;

    public CodeMsg() {
    }

    public CodeMsg(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }

    public CodeMsg fillArgs(Object... args) {
        Integer code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }
}
