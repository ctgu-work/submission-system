package com.ctgu.contributionsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author chase
 * @date 2019/10/15 0015
 */
@RestController
public class TestController {
    @RequestMapping("/index")
    public String index() {
        return "hello";
    }

}
