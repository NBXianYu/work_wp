package com.core.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author TheoWu
 * @create 2018/8/27 0027
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class JpaQuery<T> {
    private static final long serialVersionUID = 1L;

    /**
     * 匹配查询参数
     * key:查询字段
     * value:字段值
     */
    private Map<String, Object> equal = new LinkedHashMap<>();

    /**
     * 模糊查询参数
     */
    private Map<String, String> like = new LinkedHashMap<>();

    /**
     * 包含查询参数
     */
    private Map<String, List<Object>> in = new LinkedHashMap<>();

    /**
     * 小于等于查询参数
     */
    private Map<String, Comparable> lessEqual = new LinkedHashMap<>();

    /**
     * 大于等于查询参数
     */
    private Map<String, Comparable> greaterEqual = new LinkedHashMap<>();

    /**
     * 实体属性关联查询，等值查询参数
     */
    private Map<String, Object> joinEqual = new LinkedHashMap<>();

    /**
     * 实体属性关联查询，包含查询参数
     */
    private Map<String, List<Object>> joinIn = new LinkedHashMap<>();

    /**
     * 排序参数
     * key:排序字段
     * value：排序顺序
     */
    private Map<String, String> orders = new LinkedHashMap<>();

    /**
     * 当前页码
     */
    private int currPage = 1;
    /**
     * 每页条数
     */
    private int limit = 10;

    /**
     * 当前页前面的记录条数
     */
    private int offset;

    public JpaQuery() {
    }

    public JpaQuery(Map<String, Object> params) {
        if (params.get("equal") != null) {
            equal = (Map) params.get("equal");
        }
        if (params.get("like") != null) {
            like = (Map) params.get("like");
        }
        if (params.get("in") != null) {
            in = (Map) params.get("in");
        }
        if (params.get("lessEqual") != null) {
            lessEqual = (Map) params.get("lessEqual");
        }
        if (params.get("greaterEqual") != null) {
            greaterEqual = (Map) params.get("greaterEqual");
        }
        if (params.get("joinEqual") != null) {
            joinEqual = (Map) params.get("joinEqual");
        }
        if (params.get("joinIn") != null) {
            joinIn = (Map) params.get("joinIn");
        }
        if (params.get("orders") != null) {
            orders = (Map) params.get("orders");
        }
        if (params.get("page") != null) {
            currPage = Integer.parseInt((String) params.get("page"));
            if (currPage < 1) {
                currPage = 1;
            }
        }
        if (params.get("limit") != null) {
            limit = Integer.parseInt((String) params.get("limit"));
            if (limit < 1) {
                limit = 10;
            }
        }
        offset = (currPage - 1) * limit;
    }

    public JpaQuery(Map<String, Object> params, Map<String, Object> equalParam
            , Map<String, String> likeParam, Map<String, String> ordersParam) {
        if (params.get("page") != null) {
            currPage = Integer.parseInt((String) params.get("page"));
            if (currPage < 1) {
                currPage = 1;
            }
        }
        if (params.get("limit") != null) {
            limit = Integer.parseInt((String) params.get("limit"));
            if (limit < 1) {
                limit = 10;
            }
        }
        offset = (currPage - 1) * limit;

        if (equalParam != null) {
            equal = equalParam;
        }
        if (likeParam != null) {
            like = likeParam;
        }
        if (equalParam != null) {
            orders = ordersParam;
        }
    }

    public JpaQuery(Map<String, Object> equalParam, Map<String, String> likeParam, Map<String, String> ordersParam, int page, int limit) {
        if (equalParam != null) {
            equal = equalParam;
        }
        if (likeParam != null) {
            like = likeParam;
        }
        if (equalParam != null) {
            orders = ordersParam;
        }
        if (page > 0) {
            currPage = page;
        }
        if (limit > 0) {
            this.limit = limit;
        }
        offset = (currPage - 1) * limit;
    }

    /**
     * Description: 获取Specification
     *
     * @return org.springframework.data.jpa.domain.Specification<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:55
     */
    public Specification<T> getSpecification() {
        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (equal != null && equal.size() > 0) {
                    for (Map.Entry<String, Object> me : equal.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null) {
                            Predicate predicate = criteriaBuilder.equal(root.get(me.getKey()), me.getValue());
                            predicates.add(predicate);
                        }
                    }
                }
                if (like != null && like.size() > 0) {
                    for (Map.Entry<String, String> me : like.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && StringUtils.isNotBlank(me.getValue())) {
                            Predicate predicate = criteriaBuilder.like(root.get(me.getKey()), "%" + me.getValue() + "%");
                            predicates.add(predicate);
                        }
                    }
                }
                if (in != null && in.size() > 0) {
                    for (Map.Entry<String, List<Object>> me : in.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null && me.getValue().size() > 0) {
                            CriteriaBuilder.In<Object> cIn = criteriaBuilder.in(root.get(me.getKey()));
                            for (Object object : me.getValue()) {
                                cIn.value(object);
                            }
                            predicates.add(cIn);
                        }
                    }
                }

                if (lessEqual != null && lessEqual.size() > 0) {
                    for (Map.Entry<String, Comparable> me : lessEqual.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null) {
                            Predicate predicate = criteriaBuilder.lessThanOrEqualTo(root.get(me.getKey()), me.getValue());
                            predicates.add(predicate);
                        }
                    }
                }
                if (greaterEqual != null && greaterEqual.size() > 0) {
                    for (Map.Entry<String, Comparable> me : greaterEqual.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null) {
                            Predicate predicate = criteriaBuilder.greaterThanOrEqualTo(root.get(me.getKey()), me.getValue());
                            predicates.add(predicate);
                        }
                    }
                }

                if (joinEqual != null && joinEqual.size() > 0) {
                    for (Map.Entry<String, Object> me : joinEqual.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null) {
                            Predicate predicate = criteriaBuilder.equal(root.join(me.getKey(), JoinType.LEFT), me.getValue());
                            predicates.add(predicate);
                        }
                    }
                }

                if (joinIn != null && joinIn.size() > 0) {
                    for (Map.Entry<String, List<Object>> me : joinIn.entrySet()) {
                        if (StringUtils.isNotBlank(me.getKey()) && me.getValue() != null && me.getValue().size() > 0) {
                            CriteriaBuilder.In<Object> cIn = criteriaBuilder.in(root.join(me.getKey(), JoinType.LEFT));
                            for (Object object : me.getValue()) {
                                cIn.value(object);
                            }
                            predicates.add(cIn);
                        }
                    }
                }

                criteriaQuery.where(predicates.toArray(new Predicate[0]));
                return null;
            }
        };
    }

    /**
     * Description:获取Pageable
     *
     * @return org.springframework.data.domain.Pageable
     * @author Theo
     * @date 2018/8/31 0031 下午 15:55
     */
    public Pageable getPageable() {
        List<Sort.Order> orderList = new ArrayList<>();
        if (orders != null) {
            for (Map.Entry<String, String> me : orders.entrySet()) {
                if (me.getKey() == null) {
                    continue;
                }
                Sort.Order order = new Sort.Order(Sort.Direction.fromString(me.getValue()), me.getKey());
                orderList.add(order);
            }
        }
        Sort sort = Sort.by(orderList);
        return PageRequest.of(currPage - 1, limit, sort);
    }

    /**
     * Description: JpaQuery链式操作第一步，用于创建JpaQuery并定义其泛型
     *
     * @return com.yangfan.core.common.utils.JpaQuery
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public static <E> JpaQuery start() {
        return new JpaQuery<E>();
    }

    /**
     * Description: JpaQuery链式操作，等值查询
     *
     * @param key   属性名
     * @param value 属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> equal(String key, Object value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.equal.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，等值查询
     *
     * @param equalParams 等值查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> equal(Map<String, Object> equalParams) {
        if (equalParams != null && equalParams.size() > 0) {
            this.equal.putAll(equalParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，模糊查询
     *
     * @param key   属性名
     * @param value 属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> like(String key, String value) {
        if (StringUtils.isNotBlank(key) && StringUtils.isNotBlank(value)) {
            this.like.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，模糊查询
     *
     * @param likeParams 模糊查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> like(Map<String, String> likeParams) {
        if (likeParams != null && likeParams.size() > 0) {
            this.like.putAll(likeParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，包含查询
     *
     * @param key   属性名
     * @param value 属性值列表
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> in(String key, List<Object> value) {
        if (StringUtils.isNotBlank(key) && value != null && value.size() > 0) {
            this.in.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，包含查询
     *
     * @param inParams 包含查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> in(Map<String, List<Object>> inParams) {
        if (inParams != null && inParams.size() > 0) {
            this.in.putAll(inParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，小于等于查询
     *
     * @param key   属性名
     * @param value 属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> lessOrEqual(String key, Comparable value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.lessEqual.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，小于等于查询
     *
     * @param lessEqualParams 小于等于查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> lessOrEqual(Map<String, Comparable> lessEqualParams) {
        if (lessEqualParams != null && lessEqualParams.size() > 0) {
            this.lessEqual.putAll(lessEqualParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，大于等于查询
     *
     * @param key   属性名
     * @param value 属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> greaterOrEqual(String key, Comparable value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.greaterEqual.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，大于等于查询
     *
     * @param greaterEqualParams 大于等于查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> greaterOrEqual(Map<String, Comparable> greaterEqualParams) {
        if (greaterEqualParams != null && greaterEqualParams.size() > 0) {
            this.greaterEqual.putAll(greaterEqualParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，实体属性id等值查询
     *
     * @param key   属性名
     * @param value 属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> joinEqual(String key, Object value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.joinEqual.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，实体属性id等值查询
     *
     * @param joinEqualParams 实体属性id等值查询参数集合，map的key为属性名，map的value为属性值
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> joinEqual(Map<String, Object> joinEqualParams) {
        if (joinEqualParams != null && joinEqualParams.size() > 0) {
            this.joinEqual.putAll(joinEqualParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，实体属性id包含查询
     *
     * @param key   属性名
     * @param value 属性值列表
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> joinIn(String key, List<Object> value) {
        if (StringUtils.isNotBlank(key) && value != null) {
            this.joinIn.put(key, value);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，实体属性id包含查询
     *
     * @param joinInParams 实体属性id包含查询参数集合，map的key为属性名，map的value为属性值列表
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> joinIn(Map<String, List<Object>> joinInParams) {
        if (joinInParams != null && joinInParams.size() > 0) {
            this.joinIn.putAll(joinInParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，排序
     *
     * @param key   属性名
     * @param order 排序方式：ASC-升序，DESC-降序
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:56
     */
    public JpaQuery<T> order(String key, String order) {
        this.orders.put(key, order);
        return this;
    }

    /**
     * Description: JpaQuery链式操作，排序
     *
     * @param ordersParams 排序参数集合，map的key为属性名，map的value为排序方式：ASC-升序，DESC-降序
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 15:58
     */
    public JpaQuery<T> order(Map<String, String> ordersParams) {
        if (ordersParams != null && ordersParams.size() > 0) {
            this.orders.putAll(ordersParams);
        }
        return this;
    }

    /**
     * Description: JpaQuery链式操作，分页
     *
     * @param page  查询的页码
     * @param limit 每页数据量
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 16:07
     */
    public JpaQuery<T> pageAndLimit(int page, int limit) {
        if (page > 0) {
            this.currPage = page;
        }
        if (limit > 0) {
            this.limit = limit;
        }
        offset = (currPage - 1) * limit;
        return this;
    }

    /**
     * Description: JpaQuery链式操作，分页，每页数据量默认为10
     *
     * @param page 查询的页码
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 16:07
     */
    public JpaQuery<T> page(int page) {
        if (page > 0) {
            this.currPage = page;
        }
        offset = (currPage - 1) * limit;
        return this;
    }

    /**
     * Description: JpaQuery链式操作，分页，页码默认为1
     *
     * @param limit 每页数据量
     * @return com.yangfan.core.common.utils.JpaQuery<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 16:07
     */
    public JpaQuery<T> limit(int limit) {
        if (limit > 0) {
            this.limit = limit;
        }
        offset = (currPage - 1) * limit;
        return this;
    }

    /**
     * Description:JpaQuery链式操作最后一步，查询列表，不分页不排序
     *
     * @param repository 泛型T对应repository，需要继承BaseRepository或JpaSpecificationExecutor
     * @return java.util.List<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 16:09
     */
    public List<T> query(JpaSpecificationExecutor<T> repository) {
        return repository.findAll(this.getSpecification());
    }

    /**
     * Description:JpaQuery链式操作最后一步，分页查询，返回org.springframework.data.domain.Page
     *
     * @param repository 泛型T对应repository，需要继承BaseRepository或JpaSpecificationExecutor
     * @return java.util.List<T>
     * @author Theo
     * @date 2018/8/31 0031 下午 16:09
     */
    public Page<T> queryPage(JpaSpecificationExecutor<T> repository) {
        return repository.findAll(this.getSpecification(), this.getPageable());
    }
}
