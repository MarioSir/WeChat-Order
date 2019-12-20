package com.han.order.server.exception;

import com.han.result.CodeMsg;

public class OrderException extends RuntimeException {

    //private Integer code;

    private CodeMsg codeMsg;

    public OrderException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }
    /*public OrderException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public OrderException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }*/
}
