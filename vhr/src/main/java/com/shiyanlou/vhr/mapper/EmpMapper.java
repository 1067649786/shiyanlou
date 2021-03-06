package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.Employee;
import com.shiyanlou.vhr.bean.Nation;
import com.shiyanlou.vhr.bean.PoliticsStatus;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface EmpMapper {

    List<Nation> getAllNations();

    List<PoliticsStatus> getAllPolitics();

    Long getMaxWorkID();

    int addEmp(Employee employee);

    int updateEmp(@Param("emp") Employee employee);

    int deleteEmpById(@Param("ids") String[] ids);

    List<Employee> getEmployeeByPage(@Param("start") Integer start, @Param("size") Integer size, @Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId")Long  departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);

    Long getCountByKeywords(@Param("keywords") String keywords, @Param("politicId") Long politicId, @Param("nationId") Long nationId, @Param("posId") Long posId, @Param("jobLevelId") Long jobLevelId, @Param("engageForm") String engageForm, @Param("departmentId")Long  departmentId, @Param("startBeginDate") Date startBeginDate, @Param("endBeginDate") Date endBeginDate);

    int addEmps(@Param("emps") List<Employee> emps);

    Employee getEmpById(Long Id);

    List<Employee> getEmployeeByPageShort(@Param("start") int start, @Param("size") Integer size);


}
