package top.ijiujiu.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/06/24 17:21
 */
@Data
public class WeChatResponse extends WeChatError {

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("session_key")
    private String sessionKey;

    @JsonProperty("unionid")
    private String unionId;

    @Override
    public String toString() {
        return "WeChatResponse{" +
                "openId='" + openId + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", unionId='" + unionId + '\'' +
                '}' + "  " + super.toString();
    }
}
