package com.ygy.vhr.service;

import com.ygy.vhr.bean.JobLevel;
import com.ygy.vhr.mapper.JobLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobLevelService {

    @Autowired
    JobLevelMapper jobLevelMapper;

    public List<JobLevel> getAllJobLevels(){
        return jobLevelMapper.getAllJobLevels();
    }
}
