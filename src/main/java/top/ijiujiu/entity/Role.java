package top.ijiujiu.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 15:29
 */
@Data
@Entity
public class Role extends BeanBase {
    //角色代码
    private String roleCode;
    //角色名称
    private String roleName;
    //角色等级
    private Integer level;

}