package top.ijiujiu.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  响应客户端对象封装
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultUtil<T> implements Serializable{

    private Integer status;
    private String message;
    private T data = null;

    public ResultUtil<T> success(){
        this.status = SystemStatusEnum.SUCCESS.getStatus();
        this.message = SystemStatusEnum.SUCCESS.getMessage();
        return this;
    }

    public ResultUtil<T> success(String message){
        this.status = SystemStatusEnum.SUCCESS.getStatus();
        this.message = message;
        return this;
    }

    public ResultUtil<T> success(T t){
        this.status = SystemStatusEnum.SUCCESS.getStatus();
        this.message = SystemStatusEnum.SUCCESS.getMessage();
        this.data = t;
        return this;
    }

    public ResultUtil<T> success(String message,T t){
        this.status = SystemStatusEnum.SUCCESS.getStatus();
        this.message = message;
        this.data = t;
        return this;
    }

    public ResultUtil<T> failed(){
        this.status = SystemStatusEnum.FAILED.getStatus();
        this.message = SystemStatusEnum.FAILED.getMessage();
        return this;
    }

    public ResultUtil<T> failed(String message){
        this.status = SystemStatusEnum.FAILED.getStatus();
        this.message = message;
        return this;
    }

    public ResultUtil<T> noAuth(){
        this.status = SystemStatusEnum.NO_AUTH.getStatus();
        this.message = SystemStatusEnum.NO_AUTH.getMessage();
        return this;
    }

    public ResultUtil<T> noAuth(String message){
        this.status = SystemStatusEnum.NO_AUTH.getStatus();
        this.message = message;
        return this;
    }
}
