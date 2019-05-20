package com.core.modules.sys.service.spec;

import com.core.modules.sys.entity.SysUserEntity;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author 吴鹏
 * @Description:
 */
public class SysUserSpec {

    public static Specification<SysUserEntity> where (Map<String, Object> params) {

        String keyword = MapUtils.getString(params, "keyword");

        return new Specification<SysUserEntity>() {
            @Override
            public Predicate toPredicate(Root<SysUserEntity> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicateList = new ArrayList<>();

                if (StringUtils.isNotBlank(keyword)) {
                    predicateList.add(criteriaBuilder.like(root.get("userName"), "%" + keyword + "%"));
                }

                return criteriaQuery.where(predicateList.toArray(new Predicate[predicateList.size()])).distinct(true).getRestriction();
            }
        };
    }

}
