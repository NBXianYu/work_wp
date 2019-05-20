package com.core.modules.job.service.impl;

import com.core.modules.job.repository.SheduleJobRepository;
import com.core.modules.job.service.SheduleJobService;
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
