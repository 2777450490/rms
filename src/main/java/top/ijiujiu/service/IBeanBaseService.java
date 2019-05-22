package top.ijiujiu.service;

import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 通用service接口
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/21 15:20
 */
public interface IBeanBaseService<T> {

    /**
     * 根据ID查询
     * @param id
     * @return
     */
    T findById(String id);

    /**
     * 查询全部
     * @return
     */
    List<T> findAll();

    /**
     * 新增
     * @param t
     * @return
     */
    T add(T t);

    /**
     * 更新
     * @param t
     * @return
     */
    T update(T t);

    /**
     * 根据ID删除
     * @param id
     * @return
     */
    Boolean delById(String id);

    /**
     * 根据多个ID删除
     * @param ids
     * @return
     */
    Boolean delByIds(String[] ids);

    /**
     * 没有数据过滤的分页查询
     * @param page
     * @param size
     * @return
     */
    Page<T> findByPage(Integer page, Integer size);

    /**
     * 带数据过滤的分页查询
     * @param page
     * @param size
     * @param t
     * @return
     */
    Page<T> findByPage(Integer page, Integer size, T t);


}
