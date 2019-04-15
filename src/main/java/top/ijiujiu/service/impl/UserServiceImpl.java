package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.ijiujiu.entity.User;
import top.ijiujiu.repository.UserRepository;
import top.ijiujiu.service.IUserService;
import top.ijiujiu.utils.OperationTypeEnum;
import top.ijiujiu.utils.PojoUtil;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(value="transactionManager", rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService {
    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(String id) {
        logger.info("入参为:{}",id);
        return this.userRepository.findById(id).get();
    }

    @Override
    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User add(User user) {
        logger.info("入参为:{}",user);
        PojoUtil.setSysProperties(user, OperationTypeEnum.INSERT);
        logger.info("setSysPropertie后为:{}",user);
        return this.userRepository.save(user);
    }

    @Override
    public User update(User user) {
        logger.info("入参为:{}",user);
        PojoUtil.setSysProperties(user, OperationTypeEnum.UPDATE);
        logger.info("setSysPropertie后为:{}",user);
        return this.userRepository.save(user);
    }

    @Override
    public Boolean delById(String id) {
        logger.info("入参为:{}",id);
        User user = findById(id);
        try {
            PojoUtil.setSysProperties(user, OperationTypeEnum.DELETE);
            this.userRepository.save(user);
            return true;
        }catch (Exception e){
            throw new RuntimeException("删除user未找到!");
        }
    }
    @Override
    public Boolean delByIds(String[] ids) {
        for (String id:ids){
            delById(id);
        }
        return true;
    }

    @Override
    public Page<User> findByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.userRepository.findAll((root,query,criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDelete"), 0));
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            return finalPredicate;
        }, pageRequest);
    }

    @Override
    public Page<User> findByPage(Integer page, Integer size, User user) {
        return null;
    }
}
