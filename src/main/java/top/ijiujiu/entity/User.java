package top.ijiujiu.entity;


import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Date;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 15:29
 */
@Data
@Entity
public class User extends BeanBase implements UserDetails{

    // 登录名
    @Column(unique = true,nullable = false)
    private String loginName;

    // 密码
    @Column(nullable = false)
    private String pwd;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return pwd;
    }

    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
