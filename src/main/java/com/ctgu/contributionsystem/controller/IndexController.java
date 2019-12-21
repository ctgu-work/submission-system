package com.ctgu.contributionsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-20 20:54
 * @ClassName IndexController
 * @Version 1.0.0
 */
@RequestMapping("/index")
@RestController
public class IndexController {

    @GetMapping("/{id}")
    public String articleList(){
        return "1";
    }

}
