package com.shiyanlou.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

//自己手动编写Java代码来实现定制异常信息处理
public class MySpringExceptionIntegratedHandler implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, Object handler, Exception ex) {
        //这里可以根据异常的类型来决定什么样的对策
        System.out.println("异常已经被处理了");
        System.out.println("异常的类型是:" + ex.getClass().getName());
        request.setAttribute("ex",ex);
        return new ModelAndView("runtimeExceptionPage");
    }
}