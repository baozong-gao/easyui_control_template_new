package com.company.core.controller;

import com.company.core.Enum.StatisticalTypeEnum;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Created by APPLE on 15/12/29.
 */
@Controller
public class HomeController {


    @RequestMapping(value = "/home/{key:.*}")
    public String goToHomePage(@PathVariable String key) {
        StatisticalTypeEnum typeEnum = StatisticalTypeEnum.valueOf(key);
        return "home/" + typeEnum.getType();
    }

}
