package com.shiyanlou.vhr.controller;

import com.shiyanlou.vhr.bean.Menu;
import com.shiyanlou.vhr.common.HrUtils;
import com.shiyanlou.vhr.service.MenuService;
import com.shiyanlou.vhr.bean.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Autowired
    MenuService menuService;

    @RequestMapping("/sysmenu")
    public List<Menu> sysmenu(){
        return menuService.getMenusByHrId();
    }

    @RequestMapping("/hr")
    public Hr currentUser(){
        return HrUtils.getCurrentHr();
    }
}
