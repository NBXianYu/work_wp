package com.core.work.repository;


import com.core.work.entity.AbstractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Set;

/**
 * @author : 吴鹏
 * @date : 2018年12月20日 18:28:23
 * @description : jpa基础接口
 */
@NoRepositoryBean
public interface BaseRepository<T extends AbstractEntity, D> extends JpaRepository<T, D>, JpaSpecificationExecutor<T> {
    List<T> findByIdIn(List<D> ids);

    List<T> findByIdIn(Set<D> ids);

    List<T> findByIdIn(D[] ids);
}
