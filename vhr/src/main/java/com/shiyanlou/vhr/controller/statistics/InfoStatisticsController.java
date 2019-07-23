package com.shiyanlou.vhr.controller.statistics;

import com.shiyanlou.vhr.bean.Department;
import com.shiyanlou.vhr.bean.InfoStatistics;
import com.shiyanlou.vhr.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/statistics/all")
public class InfoStatisticsController {

    @Autowired
    StatisticsService statisticsService;

    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public List<InfoStatistics> getAllInfo(){

        List<InfoStatistics> infoStatisticsList=new LinkedList<>();

        List<Department> departments=new ArrayList<>();
        departments=statisticsService.getAllDeps();

        for (Department department:departments){
            InfoStatistics infoStatistics=new InfoStatistics();
            infoStatistics.setDepName(department.getName());
            infoStatistics.setPeopleCount(statisticsService.getAllPeoplebyDepId(department.getId()));
            infoStatistics.setJoinCount(statisticsService.getJoinCount(department.getId()));
            infoStatisticsList.add(infoStatistics);
        }
        return infoStatisticsList;
    }
}
