package com.ygy.vhr.mapper;

import com.ygy.vhr.bean.JobLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLevelMapper {

    List<JobLevel> getAllJobLevels();
}
