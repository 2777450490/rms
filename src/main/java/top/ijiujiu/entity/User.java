package top.ijiujiu.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 15:29
 */
@Data
@Entity
public class User extends BeanBase implements UserDetails{

    /**
     * 登录名
     */
    @Column(unique = true,nullable = false)
    private String loginName;

    /**
     * 密码
     */
    @Column(nullable = false)
    private String pwd;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 身份证号码
     */
    private Date idCardNo;

    /**
     * 电话
     */
    private String phone;

    /**
     * 地区
     */
    private String regionBh;

    /**
     * 区域
     */
    private String area;

    /**
     * 微信openid
     */
    private String openId;

    /**
     * 签名图片id
     */
    private String signImgId;

    /**
     * 头像
     */
    private String headPortrait;

    /**
     * 和角色是多对多的关系
     */
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns= {@JoinColumn(name="user_id",referencedColumnName="id")},
            inverseJoinColumns= {@JoinColumn(name="role_id",referencedColumnName="id")})
    @Where(clause="is_delete=0")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for(Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getId()));
        }
        return authorities;
    }


    @JsonIgnore
    @Override
    public String getPassword() {
        return pwd;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return loginName;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        if (status != 1){
            return false;
        }
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(isDelete != 0){
            return false;
        }
        return true;
    }
}
