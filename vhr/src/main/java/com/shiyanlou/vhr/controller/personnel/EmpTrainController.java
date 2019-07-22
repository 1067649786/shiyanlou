package com.shiyanlou.vhr.controller.personnel;

import com.shiyanlou.vhr.bean.EmpTrain;
import com.shiyanlou.vhr.bean.RespBean;
import com.shiyanlou.vhr.service.EmpService;
import com.shiyanlou.vhr.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/personnel/train")
public class EmpTrainController {

    @Autowired
    PersonnelService personnelService;

    @Autowired
    EmpService empService;

    @RequestMapping(value = "/trains", method = RequestMethod.POST)
    public RespBean addEmpTrain(EmpTrain empTrain){
        if(personnelService.addEmpTrain(empTrain)==1){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }

    @RequestMapping(value = "/trains", method = RequestMethod.GET)
    public List<EmpTrain> getAllTrains(){
        return personnelService.getAllEmpTrains();
    }

    @RequestMapping(value = "/trains", method = RequestMethod.PUT)
    public RespBean updateEmpTrain(EmpTrain empTrain){
        if(personnelService.updateEmpTrain(empTrain)==1){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }
    @RequestMapping(value = "/trains/{ids}", method = RequestMethod.DELETE)
    public RespBean deleteEmpTrain(@PathVariable String ids){
        if(personnelService.deleteEmpTrain(ids)>=1){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
