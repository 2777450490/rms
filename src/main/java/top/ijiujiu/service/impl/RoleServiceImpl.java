package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import top.ijiujiu.entity.Role;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.repository.RoleRepository;
import top.ijiujiu.service.IRoleService;
import top.ijiujiu.utils.PojoUtil;

import java.util.List;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/22 17:53
 */
@Service
public class RoleServiceImpl implements IRoleService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private RoleRepository roleRepository;


    @Override
    public Role findById(String id) {
        LOGGER.info("入参为:{}",id);
        return this.roleRepository.findById(id).get();
    }

    @Override
    public List<Role> findAll() {
        return this.roleRepository.findAll();
    }

    @Override
    public Role add(Role role) {
        LOGGER.info("入参为:{}",role);
        PojoUtil.setSysProperties(role, OperationTypeEnum.INSERT);
        return this.roleRepository.save(role);
    }

    @Override
    public Role update(Role role) {
        return null;
    }

    @Override
    public Boolean delById(String id) {
        return null;
    }

    @Override
    public Boolean delByIds(String[] ids) {
        return null;
    }

    @Override
    public Page<Role> findByPage(Integer page, Integer size) {
        return null;
    }

    @Override
    public Page<Role> findByPage(Integer page, Integer size, Role role) {
        return null;
    }
}
