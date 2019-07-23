package com.shiyanlou.vhr.service;

import com.shiyanlou.vhr.bean.AdjustSalary;
import com.shiyanlou.vhr.bean.EmpEc;
import com.shiyanlou.vhr.bean.EmpMove;
import com.shiyanlou.vhr.bean.EmpTrain;
import com.shiyanlou.vhr.mapper.PersonnelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonnelService {

    @Autowired
    PersonnelMapper personnelMapper;

    public int addEc(EmpEc empEc){
        return personnelMapper.addEc(empEc);
    }

    public int addEmpTrain(EmpTrain empTrain){
        return personnelMapper.addEmpTrain(empTrain);
    }

    public int updateEmpTrain(EmpTrain empTrain){
        return personnelMapper.updateEmpTrain(empTrain);
    }

    public int deleteEmpTrain(String ids){
        String[] split=ids.split(",");
        return personnelMapper.deleteEmpTrain(split);
    }

    public List<EmpTrain> getAllEmpTrains(){
        return personnelMapper.getAllEmpTrains();
    }


    public int addAdjustSalary(AdjustSalary adjustSalary){
        return personnelMapper.addAdjustSalary(adjustSalary);
    }

    public int updateAdjustSalary(AdjustSalary adjustSalary){
        return personnelMapper.updateAdjustSalary(adjustSalary);
    }

    public int deleteAdjustSalary(String ids){
        String[] split = ids.split(",");
        return personnelMapper.deleteAdjustSalary(split);
    }

    public List<AdjustSalary> getAllAdjustSalary(){
        return personnelMapper.getAllAdjustSalary();
    }


    public int addEmpMove(EmpMove empMove){
        return personnelMapper.addEmpMove(empMove);
    }

    public int updateEmpMove(EmpMove empMove){
        return personnelMapper.updateEmpMove(empMove);
    }

    public List<EmpMove> getAllEmpMove(){
        return personnelMapper.getAllEmpMove();
    }

    public int updateDepIdAndJobId(EmpMove empMove){
        return personnelMapper.updateDepIdAndJobId(empMove);
    }

}
