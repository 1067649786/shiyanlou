package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.JobLevel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobLevelMapper {

    List<JobLevel> getAllJobLevels();
}
