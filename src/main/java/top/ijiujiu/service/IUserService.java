package top.ijiujiu.service;

import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;
import top.ijiujiu.entity.User;

import java.util.List;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 11:12
 */
public interface IUserService extends UserDetailsService{

    User findById(String id);

    User findByLoginName(String loginName);

    List<User> findAll();

    User add(User user);

    User update(User user);

    Boolean delById(String id);

    Boolean delByIds(String[] ids);

    Page<User> findByPage(Integer page, Integer size);

    Page<User> findByPage(Integer page, Integer size, User user);
}
