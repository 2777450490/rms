package top.ijiujiu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import top.ijiujiu.entity.User;

public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
    User findByLoginName(String loginName);
}
