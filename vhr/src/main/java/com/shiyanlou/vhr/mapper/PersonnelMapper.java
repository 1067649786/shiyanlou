package com.shiyanlou.vhr.mapper;

import com.shiyanlou.vhr.bean.AdjustSalary;
import com.shiyanlou.vhr.bean.EmpEc;
import com.shiyanlou.vhr.bean.EmpMove;
import com.shiyanlou.vhr.bean.EmpTrain;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonnelMapper {

    int addEc(EmpEc empEc);

    List<EmpEc> getAllEmpEc();


    int addEmpTrain(@Param("empTrain") EmpTrain empTrain);

    int updateEmpTrain(@Param("empTrain") EmpTrain empTrain);

    int deleteEmpTrain(@Param("ids") String[] ids);

    List<EmpTrain> getAllEmpTrains();


    int addAdjustSalary(@Param("adjustSalary") AdjustSalary adjustSalary);

    int updateAdjustSalary(@Param("adjustSalary") AdjustSalary adjustSalary);

    int deleteAdjustSalary(@Param("ids") String[] ids);

    List<AdjustSalary> getAllAdjustSalary();




    int addEmpMove(@Param("empMove") EmpMove empMove);

    int updateEmpMove(@Param("empMove") EmpMove empMove);

    List<EmpMove> getAllEmpMove();

    int updateDepIdAndJobId(@Param("empMove") EmpMove empMove);
}
