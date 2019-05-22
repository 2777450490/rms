package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import top.ijiujiu.entity.Resource;
import top.ijiujiu.repository.ResourceRepository;
import top.ijiujiu.service.IResourceService;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.utils.PojoUtil;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/21 15:31
 */
@Service
public class ResourceServiceImpl implements IResourceService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private ResourceRepository resourceRepository;


    @Override
    public Resource findById(String id) {
        LOGGER.info("入参为:{}",id);
        return this.resourceRepository.findById(id).get();
    }

    @Override
    public List<Resource> findAll() {
        return this.resourceRepository.findAll();
    }

    @Override
    public Resource add(Resource resource) {
        LOGGER.info("入参为:{}",resource);
        PojoUtil.setSysProperties(resource, OperationTypeEnum.INSERT);
        LOGGER.info("setSysPropertie后为:{}",resource);
        return this.resourceRepository.save(resource);
    }

    @Override
    public Resource update(Resource resource) {
        LOGGER.info("入参为:{}",resource);
        PojoUtil.setSysProperties(resource, OperationTypeEnum.UPDATE);
        LOGGER.info("setSysPropertie后为:{}",resource);
        return this.resourceRepository.save(resource);
    }

    @Override
    public Boolean delById(String id) {
        LOGGER.info("入参为:{}",id);
        Resource resource = findById(id);
        try {
            PojoUtil.setSysProperties(resource, OperationTypeEnum.DELETE);
            this.resourceRepository.save(resource);
            return true;
        }catch (Exception e){
            throw new RuntimeException("未找到删除对象!");
        }
    }

    @Override
    public Boolean delByIds(String[] ids) {
        LOGGER.info("入参为:{}",ids.toString());
        for (String id:ids){
            delById(id);
        }
        return true;
    }

    @Override
    public Page<Resource> findByPage(Integer page, Integer size) {
        LOGGER.info("入参为:{}",page,size);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.resourceRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDelete"), 0));
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            LOGGER.info("构建条件为:{}", finalPredicate);
            return finalPredicate;
        }, pageRequest);
    }

    @Override
    public Page<Resource> findByPage(Integer page, Integer size, Resource resource) {
        return null;
    }

    @Override
    public Resource getResourceByUrl(String resourceUrl) {
        return this.resourceRepository.findByResourceUrlAndIsDelete(resourceUrl,0);
    }
}
