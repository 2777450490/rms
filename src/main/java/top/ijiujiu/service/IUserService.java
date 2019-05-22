package top.ijiujiu.service;

import top.ijiujiu.entity.User;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/20 11:12
 */
public interface IUserService extends IBeanBaseService<User>{

    User findByLoginName(String loginName);
}
