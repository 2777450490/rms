package top.ijiujiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ijiujiu.entity.Role;

public interface RoleRepository extends JpaRepository<Role,String>,JpaSpecificationExecutor<Role> {

}
