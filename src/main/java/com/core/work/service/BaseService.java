package com.core.work.service;

import com.core.work.entity.AbstractEntity;

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
    List<T> findByParams(Map params);

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

}
