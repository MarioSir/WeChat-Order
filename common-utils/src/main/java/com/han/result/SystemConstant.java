package com.han.result;

public class SystemConstant{

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg FAIL = new CodeMsg(500100, "fail");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500101, "服务端异常");
    public static CodeMsg PARAM_CHECK_ERROR = new CodeMsg(500102, "参数校验异常：%s");
    public static CodeMsg PARAM_ERROR = new CodeMsg(500103, "参数信息错误");
}
