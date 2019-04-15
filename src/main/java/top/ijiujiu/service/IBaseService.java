package top.ijiujiu.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IBaseService<T> {
    T findById(String id);
    List<T> findAll();
    T add(T t);
    T update(T t);
    Boolean delById(String id);
    Boolean delByIds(String[] ids);
    Page<T> findByPage(Integer page,Integer size);
    Page<T> findByPage(Integer page,Integer size,T t);
}
