package com.ni.controller;

import com.ni.entity.User;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ValidationController
 * @description 测试bean validation
 * @Author zihao.ni
 * @Date 2023/4/28 09:57
 * @Version 1.0
 **/
@RestController
@RequestMapping("validation")
@Validated
public class ValidationController {
    @RequestMapping("test")
    public String validation(@RequestBody @Validated User user) {
        System.out.println(user);
        return "success";
    }
}