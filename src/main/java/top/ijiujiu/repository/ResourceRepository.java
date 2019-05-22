package top.ijiujiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ijiujiu.entity.Resource;

public interface ResourceRepository extends JpaRepository<Resource,String>,JpaSpecificationExecutor<Resource> {

    Resource findByResourceUrlAndIsDelete(String resourceUrl,int isDelete);
}
