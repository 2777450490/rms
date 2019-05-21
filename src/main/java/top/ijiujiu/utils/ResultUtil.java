package top.ijiujiu.utils;


import lombok.Data;

import java.io.Serializable;

/**
 *  响应客户端对象封装
 * @param <T>
 */
@Data
public class ResultUtil<T> implements Serializable{

    private static final int SUCCESS = 200;
    private static final int FAILED = 401;
    private static final int NO_AUTH = 403;
    private Integer status;
    private String message;
    private T data = null;

    public ResultUtil<T> success(){
        this.status = SUCCESS;
        this.message = "操作成功!";
        return this;
    }

    public ResultUtil<T> success(String message){
        this.status = SUCCESS;
        this.message = message;
        return this;
    }

    public ResultUtil<T> success(T t){
        this.status = SUCCESS;
        this.message = "操作成功!";
        this.data = t;
        return this;
    }

    public ResultUtil<T> success(String message,T t){
        this.status = SUCCESS;
        this.message = message;
        this.data = t;
        return this;
    }

    public ResultUtil<T> failed(){
        this.status = FAILED;
        this.message = "操作失败!";
        return this;
    }

    public ResultUtil<T> failed(String message){
        this.status = FAILED;
        this.message = message;
        return this;
    }

    public ResultUtil<T> noAuth(){
        this.status = NO_AUTH;
        this.message = "没有权限!";
        return this;
    }

    public ResultUtil<T> noAuth(String message){
        this.status = NO_AUTH;
        this.message = message;
        return this;
    }


}
