package com.lucky.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author: lenka
 * @date: 2023-05-05 10:12 AM
 */
@ApiIgnore
@Controller
public class ViewController {

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping({ "/index" ,"/"})
    public String index() {
        return "index";
    }
}
