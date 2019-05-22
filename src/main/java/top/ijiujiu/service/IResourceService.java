package top.ijiujiu.service;

import top.ijiujiu.entity.Resource;

/**
 * 资源Service
 * @author pengxl
 * @version 1.0
 * 创建时间: 2019/05/21 15:03
 */
public interface IResourceService extends IBeanBaseService<Resource>{

    Resource getResourceByUrl(String resourceUrl);
}
