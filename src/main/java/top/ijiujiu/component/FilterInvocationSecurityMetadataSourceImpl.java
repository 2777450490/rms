package top.ijiujiu.component;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import top.ijiujiu.entity.Resource;
import top.ijiujiu.entity.Role;
import top.ijiujiu.service.IResourceService;

import java.util.Collection;
import java.util.List;

/**
 * 拦截获取用户请求的URL所需要的角色放到SecurityConfig中
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/21 14:19
 */
@Component
public class FilterInvocationSecurityMetadataSourceImpl implements FilterInvocationSecurityMetadataSource {

    private final static Logger LOGGER = LoggerFactory.getLogger(FilterInvocationSecurityMetadataSourceImpl.class);

    @Autowired
    private UncurbedProperties uncurbedProperties;

    @Autowired
    private IResourceService resourceService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        // 得到用户的请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        LOGGER.info("用户请求的地址是:{}",requestUrl);
        if (uncurbedProperties.getUrl().equals(requestUrl)) {// 如果登录页面就不需要权限
            return null;
        }
        Resource resource = resourceService.getResourceByUrl(requestUrl);
        if(ObjectUtils.isEmpty(resource)) { //如果没有匹配的url则说明大家都可以访问
            return SecurityConfig.createList(uncurbedProperties.getRole());
        }
        //将resource所需要到的roles按框架要求封装返回（ResourceService里面的getRoles方法是基于RoleRepository实现的）
        Hibernate.initialize(resource.getRoles());
        List<Role> roles = resource.getRoles();
        int size = roles.size();
        String[] values = new String[size];
        for (int i = 0; i < size; i++) {
            values[i] = roles.get(i).getId();
        }
        return SecurityConfig.createList(values);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
