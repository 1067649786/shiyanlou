package com.shiyanlou.vhr.service;

import com.shiyanlou.vhr.mapper.JobLevelMapper;
import com.shiyanlou.vhr.bean.JobLevel;
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
