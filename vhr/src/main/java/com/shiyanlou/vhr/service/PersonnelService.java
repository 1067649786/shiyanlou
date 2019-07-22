package com.shiyanlou.vhr.service;

import com.shiyanlou.vhr.bean.EmpEc;
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


}
