package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import top.ijiujiu.entity.User;
import top.ijiujiu.repository.UserRepository;
import top.ijiujiu.service.IUserDetailsService;

/**
 * 用户认证使用
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/22 15:21
 */
@Service
public class UserDetailsServiceImpl implements IUserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        LOGGER.info("入参为:{}",loginName);
        User user = this.userRepository.findByLoginName(loginName);
        if (ObjectUtils.isEmpty(user)){
            throw new UsernameNotFoundException("找不到指定的用户信息!");
        }
        return user;
    }
}
