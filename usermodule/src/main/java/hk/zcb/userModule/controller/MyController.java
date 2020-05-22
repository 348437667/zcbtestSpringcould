package hk.zcb.userModule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {
    @RequestMapping(value = "getIndex")
    public String getIndex(){
        return "index";
    }
}
