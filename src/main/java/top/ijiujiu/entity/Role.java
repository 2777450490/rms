package top.ijiujiu.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 角色信息
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 15:29
 */
@Data
@Entity
@Where(clause="is_delete<>1")
public class Role extends BeanBase {

    /**
     * 角色代码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色等级
     */
    private Integer level;

    /**
     * 和用户多对多关系
     */
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @OneToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @Where(clause="is_delete<>1")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<User> users;


    /**
     * 和资源多对多
     */
    @JoinColumn(name = "role_id", foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @OneToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @Where(clause="is_delete<>1")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Resource> resources;


}