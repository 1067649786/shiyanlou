package com.shiyanlou.vhr.controller;

import com.shiyanlou.vhr.bean.RespBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegLoginController {

    @RequestMapping("/login_p")
    public RespBean login(){
        return RespBean.error("尚未登陆，请登陆！");
    }

}
