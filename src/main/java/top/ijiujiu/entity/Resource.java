package top.ijiujiu.entity;

import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

/**
 * 资源信息
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/21 10:53
 */
@Data
@Entity
public class Resource extends BeanBase {

    /**
     * 资源名称
     */
    private String resourceName;

    /**
     * 资源的url
     */
    private String resourceUrl;

    /**
     * 排序编号
     */
    private int orderNum;

    /**备注*/
    private String remark;

    /**
     * 和角色多对多
     */
    @ManyToMany(mappedBy = "resources",fetch = FetchType.LAZY)
    @Where(clause="is_delete=0")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Role> roles;

    /**
     * 自关联父对象
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id",foreignKey = @ForeignKey(name = "none",value = ConstraintMode.NO_CONSTRAINT))
    @NotFound(action = NotFoundAction.IGNORE)
    private Resource resource;

    /**
     * 自关联孩子对象
     */
    @OneToMany(mappedBy = "resource",fetch = FetchType.LAZY)
    @OrderBy("orderNum ASC")
    @Where(clause="is_delete=0")
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Resource> children;

}
