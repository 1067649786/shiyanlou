package com.shiyanlou.vhr.controller.personnel;

import com.shiyanlou.vhr.bean.EmpEc;
import com.shiyanlou.vhr.bean.RespBean;
import com.shiyanlou.vhr.service.EmpService;
import com.shiyanlou.vhr.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personnel/ec")
public class EmpEcController {

    @Autowired
    EmpService empService;

    @Autowired
    PersonnelService personnelService;

    @RequestMapping(value = "/addec", method = RequestMethod.POST)
    public RespBean addEc(EmpEc ec){

        if(empService.getEmpById(ec.getEid())==null){
            return RespBean.error("员工号不存在");
        }
        //Long eid=empService.getId(name, workID);
        if(personnelService.addEc(ec)==1){
            return RespBean.ok("添加成功!");
        }
        return RespBean.error("添加失败");
    }
}
