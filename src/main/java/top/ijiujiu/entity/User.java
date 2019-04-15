package top.ijiujiu.entity;


import lombok.Data;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * 用户信息
 */
@Data
@Entity
public class User extends BeanBase{

    // 登录名
    @Column(unique = true,nullable = false)
    private String loginName;

    // 密码
    @Column(nullable = false)
    private String password;

    // 名称
    private String name;

    // 性别
    private Integer sex;

    // 出生日期
    private Date birthday;

    // 身份证号码
    private Date idCardNo;

    // 电话
    private String phone;

    // 地区
    private String regionBh;

    // 区域
    private String area;

    // 微信openid
    private String openId;

    // 签名图片id
    private String signImgId;

    // 头像
    private String headPortrait;


}
