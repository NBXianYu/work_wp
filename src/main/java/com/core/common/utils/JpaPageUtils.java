package com.core.common.utils;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/***
 * @Author: 吴鹏
 * @Description: jpa 传入参数生成 PageRequest
 */
public class JpaPageUtils {

    /***
     * @Author: 吴鹏
     * @Description: 传入请求的参数，并获取PageRequest
     * @Params: currPage：第几页；   pageSize： 一页多少条数据
     *          orderField：排序字段，多个字段排序以下划线分开，例如：name_description_uesrName
     *          sort：升序：ASC；降序 DESC
     */
    public static PageRequest getPageRequest(Map<String, Object> params) {
        Integer pageNumber = MapUtils.getInteger(params, "currPage");
        Integer pageSize = MapUtils.getInteger(params, "pageSize");
        String sort = MapUtils.getString(params, "sort");
        String orderField = MapUtils.getString(params, "orderField");
        return pageRequest(pageNumber, pageSize, orderField, sort);
    }

    /***
     * @Author: 吴鹏
     * @Description: 也可以直接传入参数去调用 pageRequest方法
     */
    public static PageRequest getPageRequest(Integer pageIndex, Integer pageSize, String sortField, String sort) {
        return pageRequest(pageIndex, pageSize, sortField, sort);
    }

    private static PageRequest pageRequest(Integer pageIndex, Integer pageSize, String sortField, String sort) {
        //默认页面为0
        if (null == pageIndex || pageIndex < 1) {
            pageIndex = 0;
        } else {
            pageIndex = pageIndex - 1;
        }
        //默认页面大小10
        if (null == pageSize || pageSize < 1) {
            pageSize = 10;
        }

        if (StringUtils.isBlank(sort)|| StringUtils.isBlank(sortField)) {
            return new PageRequest(pageIndex, pageSize, new Sort(Sort.Direction.DESC, "gmtCreate"));
        }

        String[] sortFields=sortField.split("_");

        List<Sort.Order> orderList=new ArrayList<>();

        //发布状态排序为升序
        if ("ASC".equals(sort)) {
            for(int i=0;i<sortFields.length;i++){
                orderList.add(new Sort.Order(Sort.Direction.ASC,sortFields[i]));
            }
            orderList.add(new Sort.Order(Sort.Direction.ASC,"gmtCreate"));
            return new PageRequest(pageIndex, pageSize, new Sort(orderList));
        } else {
            orderList.add(new Sort.Order(Sort.Direction.DESC,"gmtCreate"));

            for(int i=0;i<sortFields.length;i++){
                orderList.add(new Sort.Order(Sort.Direction.DESC,sortFields[i]));
            }
            return new PageRequest(pageIndex, pageSize, new Sort(orderList));
        }
    }

}
