package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.EmpEc;
import com.shiyanlou.vhr.bean.EmpTrain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelMapper {

    int addEc(EmpEc empEc);


    int addEmpTrain(@Param("empTrain") EmpTrain empTrain);

    int updateEmpTrain(@Param("empTrain") EmpTrain empTrain);

    int deleteEmpTrain(@Param("ids") String[] ids);

    List<EmpTrain> getAllEmpTrains();
}
