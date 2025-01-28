package com.nuriapp.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("")
@Controller
public class RouterConroller { // 모든 페이지의 영역을 프론트의 /으로 반환시키기 위한 작업

    @RequestMapping(value = "/{path:[^\\.]*}")
    public String redirect() {
        return "forward:/";
    }
}
