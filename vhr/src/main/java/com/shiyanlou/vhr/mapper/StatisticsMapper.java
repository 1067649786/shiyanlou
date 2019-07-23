package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticsMapper {

    int getJoinCount(Long depId);

    int getAllPeoplebyDepId(Long depId);

    List<Department> getAllDeps();
}
