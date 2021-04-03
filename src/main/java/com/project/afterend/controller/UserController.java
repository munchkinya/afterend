package com.project.afterend.controller;



import com.project.afterend.model.ApiResponse;
import com.project.afterend.service.AdminInfoService;
import com.project.afterend.util.ApiResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:joe
 * @date:2019/5/20 19:38
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AdminInfoService adminInfoService;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse getUser(){
        return ApiResponseUtil.getApiResponse(adminInfoService.getLogin(null));
    }
}
