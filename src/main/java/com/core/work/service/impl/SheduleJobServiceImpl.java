package com.core.work.service.impl;

import com.core.work.repository.SheduleJobRepository;
import com.core.work.service.SheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class SheduleJobServiceImpl implements SheduleJobService {

    @Autowired
    private SheduleJobRepository sheduleJobRepository;


    @Override
    public void deleteByName(String id) {
        sheduleJobRepository.deleteByName(id);
    }
}
