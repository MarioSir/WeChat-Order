package com.han.result;

import lombok.Data;

/**
 * 结果对象
 * @param <T>
 */
@Data
public class Result<T> {
    private Integer code;
    private String msg;
    private T data;

    private Result() {
    }

    private Result(T data) {
        this.data = data;
    }

    private Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Result(CodeMsg codeMsg) {
        if(codeMsg != null) {
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
    }

    public Result(CodeMsg codeMsg, T data) {
        if(codeMsg!=null){
            this.code = codeMsg.getCode();
            this.msg = codeMsg.getMsg();
        }
        this.data=data;
    }
    /**
     * 成功时候调用
     * @return
     */
    public static <T>Result<T> SUCCESS(){
        return new Result(SystemConstant.SUCCESS);
    }
    /**
     * 成功时候调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T>Result<T> SUCCESS(T data){
        return new Result(SystemConstant.SUCCESS,data);
    }

    /**
     * 失败时候的调用
     * @param <T>
     * @return
     */
    public static  <T> Result<T> FAIL(){
        return new Result(SystemConstant.FAIL);
    }

    /**
     * 失败时候的调用
     * @param data
     * @param <T>
     * @return
     */
    public static  <T> Result<T> FAIL(T data){
        return new Result(SystemConstant.FAIL,data);
    }

    /**
     * 异常时候的调用
     * @param <T>
     * @return
     */
    public static  <T> Result<T> ERROR(){
        return new Result(SystemConstant.SERVER_ERROR);
    }

    /**
     * 异常时候的调用
     * @param data
     * @param <T>
     * @return
     */
    public static  <T> Result<T> ERROR(T data){
        return new Result(SystemConstant.SERVER_ERROR,data);
    }

    public static <T>Result<T> ERROR(CodeMsg codeMsg) {
        return new Result(codeMsg);
    }


    /*public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(ResultEnum resultEnum, T data) {
        this(resultEnum.code,resultEnum.msg,data);
    }
    public static <T>Result<T> SUCCESS(T data){
        return new Result(ResultEnum.SUCCESS.code, ResultEnum.SUCCESS.msg,data);
    }
    public static <T>Result<T> FAIL(T data){
        return new Result(ResultEnum.FIAL.code, ResultEnum.FIAL.msg,data);
    }
    public static <T>Result<T> ERROR(T data){
        return new Result(ResultEnum.ERROR.code, ResultEnum.ERROR.msg,data);
    }
    @Getter
    static enum ResultEnum{
        SUCCESS(0,"成功"),FIAL(1,"失败"),ERROR(9999,"系统异常");
        private int code;
        private String msg;

        ResultEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }*/
}
