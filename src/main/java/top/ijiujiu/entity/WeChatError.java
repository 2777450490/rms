package top.ijiujiu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/06/24 17:23
 */
@Data
public class WeChatError implements Serializable{

    @JsonProperty("errcode")
    private Integer errCode;

    @JsonProperty("errmsg")
    private String errMsg;

    @Override
    public String toString() {
        return "WeChatError{" +
                "errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}
