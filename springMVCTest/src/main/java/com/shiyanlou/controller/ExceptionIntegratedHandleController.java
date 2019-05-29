
package com.shiyanlou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ExceptionIntegratedHandleController {

    @RequestMapping("/exceptionIntegratedHandleController/{id}")
    @ResponseBody
    public Object hello(@PathVariable String id) {

        if (id.equals("1")) {
            throw new RuntimeException("我这里是手动抛出的异常,期望被SpringMVC集中处理");
        } else if (id.equals("2")) {
            int value = 1 / 0;
            return "手动运算错误";
        } else {
            return "no exception";
        }
    }
}
