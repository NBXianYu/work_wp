package com.core.modules.sys.service;

import com.core.common.utils.Result;
import com.core.modules.sys.entity.AbstractEntity;

import java.util.List;
import java.util.Map;

/**
 * @Author 吴鹏
 * @Date Created in 上午 11:40 2019/1/28 0028
 * @Email 694798354@qq.com
 * @Description:
 */
public interface BaseService<T extends AbstractEntity> {

    /**
     * @Description: 查询所有数据
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: []
     * @return java.util.List<T>
     * @date 2019/1/28 0028 上午 11:44
     */
    List<T> findAll();

    /**
     * @Description: 用户分页-条件查询
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: params 查询条件，翻页参数等
     * @return java.util.List<T>
     * @date 2019/1/28 0028 上午 11:43
     */
    List<T> findByParams(Map<String, Object> params);

    Result getHome(Map<String, Object> params);

    /**
     * @Description: 根据ID列表删除数据
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [ids]
     * @return void
     * @date 2019/1/28 0028 上午 11:44
     */
    void deleteByIds(List<String> ids);

    /**
     * @Description: 根据id查询
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [id]
     * @return T
     * @date 2019/1/30 0030 下午 18:21
     */
    T findById(String id);

    /**
     * @Description: 根据id查询
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [id]
     * @return T
     * @date 2019/1/30 0030 下午 18:21
     */
    List<T> findByIds(List<String> ids);

    /**
     * @Description: 根据实体的List获取他们的id 的List
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [entityList]
     * @return java.util.List<java.lang.String>
     * @date 2019/3/12 0012 上午 11:07
     */
    List<String> getIdList(List<T> entityList);

    /**
     * @Description: 根据Id 判断是否存在这个实体
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [id]
     * @return boolean
     * @date 2019/3/12 0012 上午 11:08
     */
    boolean exist(String id);

    /**
     * @Description: 新增一个实体
     * @Author: 吴鹏
     * @Email: 694798354@qq.com
     * @Param: [entity]
     * @return T
     * @date 2019/3/19 0019 上午 11:56
     */
    T save(T entity);
}
