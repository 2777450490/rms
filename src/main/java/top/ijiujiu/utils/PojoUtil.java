package top.ijiujiu.utils;


import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.ijiujiu.entity.BeanBase;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.enums.SystemStatusEnum;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

public class PojoUtil {
    /**
     * 设置BeanBase属性值
     * @param beanBase
     * @param operationType
     */
    @SuppressWarnings("deprecation")
    public static void setSysProperties(BeanBase beanBase, OperationTypeEnum operationType) {
        Assert.notNull(beanBase);
        HttpSession session = ThreadSessionUtil.getHttpSession();
        if(ObjectUtils.isEmpty(session)){
            return;
        }
        Map<String, Object> data = ThreadSessionUtil.getHttpData();
        String userIp = ObjectUtils.isEmpty(data.get("remoteIp")) ? "127.0.0.1" : data.get("remoteIp").toString();
        String userName = StringUtils.isEmpty(UserSessionUtil.getSessionUserName(session)) ? "" : UserSessionUtil.getSessionUserName(session);
        switch (operationType.getStatus()) {
            case 1:
                beanBase.setCreatedBy(userName);
                beanBase.setCreationDate(new Date());
                beanBase.setLastUpdateDate(new Date());
                beanBase.setLastUpdatedBy(userName);
                beanBase.setLastUpdateIp(userIp);
                beanBase.setVersion(0);
                break;
            case 2:
                beanBase.setDeleteBy(userName);
                beanBase.setIsDelete(1);
                beanBase.setDeleteDate(new Date());
                beanBase.setStatus(SystemStatusEnum.DISABLED.getStatus());
                break;
            case 3:
                beanBase.setLastUpdateDate(new Date());
                beanBase.setLastUpdatedBy(userName);
                beanBase.setLastUpdateIp(userIp);
                if (ObjectUtils.isEmpty(beanBase.getStatus())){
                    beanBase.setStatus(SystemStatusEnum.ENABLED.getStatus());
                }
        }
    }



}
