package com.xa.dt.ims.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/rest")
public class TestController {

    @ResponseBody
    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome(@RequestParam("name") String name) {
        log.info("用户[{}]访问welcome方法", name);
        return "hi " + name;
    }
}
