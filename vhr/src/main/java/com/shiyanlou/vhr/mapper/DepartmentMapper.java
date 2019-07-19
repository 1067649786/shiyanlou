package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentMapper {

    List<Department> getDepByPid(Long pid);

    List<Department> getAllDeps();
}
