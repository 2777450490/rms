package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.ijiujiu.entity.User;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.repository.UserRepository;
import top.ijiujiu.service.IUserService;
import top.ijiujiu.utils.EncryptUtil;
import top.ijiujiu.utils.PojoUtil;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
//@Transactional(value="transactionManager", rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findById(String id) {
        LOGGER.info("入参为:{}",id);
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User add(User user) {
        LOGGER.info("入参为:{}",user);
        user.setPwd(EncryptUtil.encrypt(user.getPwd()));
        PojoUtil.setSysProperties(user, OperationTypeEnum.INSERT);
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        LOGGER.info("入参为:{}",user);
        PojoUtil.setSysProperties(user, OperationTypeEnum.UPDATE);
        return this.userRepository.save(user);
    }

    @Override
    public Boolean delById(String id) {
        LOGGER.info("入参为:{}",id);
        User user = findById(id);
        try {
            PojoUtil.setSysProperties(user, OperationTypeEnum.DELETE);
            this.userRepository.save(user);
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
    public Page<User> findByPage(Integer page, Integer size) {
        LOGGER.info("入参为:{}",page,size);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.userRepository.findAll((root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get("isDelete"), 1));
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            return finalPredicate;
        }, pageRequest);
    }

    @Override
    public Page<User> findByPage(Integer page, Integer size, User user) {
        LOGGER.info("入参为:{}",page,size,user);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.userRepository.findAll((root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get("isDelete"), 1));
            if (!ObjectUtils.isEmpty(user)){
                if (!StringUtils.isEmpty(user.getName())){
                    predicates.add(criteriaBuilder.like(root.get("name"), "%" + user.getName() + "%"));
                }
                if (user.getSex() != null){
                    predicates.add(criteriaBuilder.equal(root.get("sex"), user.getSex()));
                }
            }
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            return finalPredicate;
        }, pageRequest);
    }

    @Override
    public User findByLoginName(String loginName) {
        LOGGER.info("入参为:{}",loginName);
        return this.userRepository.findByLoginName(loginName);
    }
}
