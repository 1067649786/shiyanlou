package com.ygy.vhr.mapper;

import com.ygy.vhr.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> getDepByPid(Long pid);

    List<Department> getAllDeps();
}
