package top.ijiujiu.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import top.ijiujiu.entity.Resource;
import top.ijiujiu.enums.OperationTypeEnum;
import top.ijiujiu.repository.ResourceRepository;
import top.ijiujiu.service.IResourceService;
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

    private final static Logger LOGGER = LoggerFactory.getLogger(ResourceServiceImpl.class);

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
        return this.resourceRepository.save(resource);
    }

    @Override
    public Resource update(Resource resource) {
        LOGGER.info("入参为:{}",resource);
        PojoUtil.setSysProperties(resource, OperationTypeEnum.UPDATE);
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
        LOGGER.info("入参为:{}", StringUtils.arrayToCommaDelimitedString(ids));
        for (String id:ids){
            delById(id);
        }
        return true;
    }

    @Override
    public Page<Resource> findByPage(Integer page, Integer size) {
        LOGGER.info("入参为:{}",page,size);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.resourceRepository.findAll(pageRequest);
    }

    @Override
    public Page<Resource> findByPage(Integer page, Integer size, Resource resource) {
        LOGGER.info("入参为:{}", page, size, resource);
        PageRequest pageRequest = PageRequest.of(page,size);
        return this.resourceRepository.findAll((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.notEqual(root.get("isDelete"), 1));
            if (!ObjectUtils.isEmpty(resource)){
                if (!StringUtils.isEmpty(resource.getResourceName())){
                    predicates.add(criteriaBuilder.like(root.get("resourceName"), "%" + resource.getResourceName() + "%"));
                }
                if (!StringUtils.isEmpty(resource.getResourceType())){
                    predicates.add(criteriaBuilder.equal(root.get("resourceType"), resource.getResourceType()));
                }
            }
            Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[]{}));
            return finalPredicate;
        }, pageRequest);

    }

}
