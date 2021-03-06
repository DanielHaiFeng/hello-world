package com.xa.dt.ims.portal.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
@Slf4j
public class IndexController {

    @RequestMapping(value = "/first", method = RequestMethod.GET)
    public String first(Model model) {
        log.info("访问index页面");
        JSONObject person = new JSONObject();
        person.put("name", "张三");
        person.put("age", 22);

        List<JSONObject> people = new ArrayList<JSONObject>();
        JSONObject p1 = new JSONObject();
        p1.put("name", "李四");
        p1.put("age", 23);
        people.add(p1);

        JSONObject p2 = new JSONObject();
        p2.put("name", "王五");
        p2.put("age", 24);
        people.add(p2);

        JSONObject p3 = new JSONObject();
        p3.put("name", "赵六");
        p3.put("age", 25);
        people.add(p3);

        model.addAttribute("person", person);
        model.addAttribute("people", people);

        log.info("数据准备完毕，跳转至index页面");

        return "index";
    }
}
