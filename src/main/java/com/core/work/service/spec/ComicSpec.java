package com.core.work.service.spec;

import com.core.work.entity.ComicEntity;
import com.core.work.utils.DateUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author 吴鹏
 * @Description: 漫画查询类
 */
public class ComicSpec {

    public static Specification<ComicEntity> where(Map<String, Object> params) {
        String keyword = MapUtils.getString(params, "keyword");
        Long time = MapUtils.getLong(params,"startTime");

        return new Specification<ComicEntity>() {

            @Override
            public Predicate toPredicate(Root<ComicEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();

                /**
                 *  PS：criteriaBuilder一般用like, equal，greaterThanOrEqualTo，lessThanOrEqualTo
                 *  方法意思就是方法名字的字面意思
                 *  第一个参数root.get("xxx")里面的xx传入的是Entity实体里面的属性名
                 */

                if (StringUtils.isNotBlank(keyword)) {
                    predicateList.add(criteriaBuilder.like(root.get("title"), "%" + keyword + "%"));
                }

                if (time != null) {
                    // 一天的开始时间
                    Date startTime = DateUtils.getFirstTimeOfDay(new Date(time));
                    // 一天的结束时间
                    Date endTime = DateUtils.getEndTimeOfDay(new Date(time));
                    predicateList.add(criteriaBuilder.between(root.get("gmtCreate"), startTime, endTime));
                }
                // distinct(true) 去重的意思
                return criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).distinct(true).getRestriction();
            }
        };
    }

}
