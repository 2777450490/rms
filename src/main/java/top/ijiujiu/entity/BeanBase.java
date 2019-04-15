package top.ijiujiu.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class BeanBase implements Serializable,Cloneable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(length = 32)
    protected String id;

    //是否删除
    @Column(name = "is_delete")
    protected Integer isDelete = 0;

    //删除时间
    @Column(name = "delete_date")
    protected Date deleteDate;

    //删除人
    @Column(name = "delete_by", length = 50)
    protected String deleteBy;

    //创建人
    @Column(name = "created_by", updatable = false, length = 50)
    protected String createdBy;

    //创建时间
    @Column(name = "creation_date", updatable = false)
    protected Date creationDate;

    //最后更新时间
    @Column(name = "last_update_date")
    protected Date lastUpdateDate;

    //最后更新人
    @Column(name = "last_updated_by", length = 50)
    protected String lastUpdatedBy;

    //最后更新ip
    @Column(name = "last_update_ip", length = 50)
    protected String lastUpdateIp;

    //版本(乐观锁)
    @Column(name = "version",columnDefinition = "INT default 0")
    @Version
    protected Integer version = 0;

    //系统状态 1-启用，2-禁用、冻结，3-系统异常
    private Integer status = 1;

    //说明（备注）
    private String remark;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
