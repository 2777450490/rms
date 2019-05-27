package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.ijiujiu.entity.Role;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.repository.RoleRepository;
import top.ijiujiu.service.IRoleService;
import top.ijiujiu.utils.PojoUtil;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
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
        LOGGER.info("入参为:{}",role);
        PojoUtil.setSysProperties(role, OperationTypeEnum.UPDATE);
        return this.roleRepository.save(role);
    }

    @Override
    public Boolean delById(String id) {
        LOGGER.info("入参为:{}",id);
        Role role = findById(id);
        try {
            PojoUtil.setSysProperties(role, OperationTypeEnum.DELETE);
            this.roleRepository.save(role);
            return true;
        }catch (Exception e){
            throw new RuntimeException("未找到删除对象!");
        }
    }

    @Override
    public Boolean delByIds(String[] ids) {
        LOGGER.info("入参为:{}", StringUtils.arrayToCommaDelimitedString(ids));
        for (String id:ids){
            delById(id);
        }
        return true;
    }

    @Override
    public Page<Role> findByPage(Integer page, Integer size) {
        LOGGER.info("入参为:{}",page,size);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.roleRepository.findAll(pageRequest);
    }

    @Override
    public Page<Role> findByPage(Integer page, Integer size, Role role) {
        LOGGER.info("入参为:{}",page,size,role);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.roleRepository.findAll((root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!ObjectUtils.isEmpty(role)){
                if (!StringUtils.isEmpty(role.getRoleName())){
                    predicates.add(criteriaBuilder.like(root.get("roleName"), "%" + role.getRoleName() + "%"));
                }
                if (!StringUtils.isEmpty(role.getRoleCode())){
                    predicates.add(criteriaBuilder.like(root.get("roleCode"), "%" + role.getRoleCode() + "%"));
                }
            }
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            return finalPredicate;
        }, pageRequest);
    }
}
