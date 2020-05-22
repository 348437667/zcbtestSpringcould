package hk.zcb.userModule.controller;

import com.alibaba.fastjson.JSONObject;
import hk.zcb.userModule.dao.entity.User;
import hk.zcb.userModule.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    public UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/holle")
    public String holle() {
        System.out.println("111111111111");
        userService.t1est();
        return "holle Spring";
    }

    @RequestMapping("/login.do")
    public String login(@RequestParam(value = "admin_name", defaultValue = "") String adminName,
                        @RequestParam(value = "password", defaultValue = "") String password) {
//    public String login(@RequestBody @ModelAttribute User user) {//, @RequestParam("I_D") String id
        JSONObject rr = new JSONObject();
//        if (!(strCode.toUpperCase().equals(session.getAttribute("strCode")))) {
//            rr.put("state",0) ;
//            rr.put("message","验证码错误") ;
//            return rr.toJSONString();
//        }


        if ("".equals(adminName) || "".equals(password)) {
            rr.put("state", 0);
            rr.put("message", "用户名密码不可为空");
        } else {
            try {
                User user = userService.login(adminName);
                rr.put("state", 1);
                rr.put("message", "登录成功");
                rr.put("name", user.getName());
//                session.setAttribute("user", user);
            } catch (Exception e) {
                e.printStackTrace();
                rr.put("state", 0);
                rr.put("message", e.getMessage());
            }
        }
        System.out.println(rr);
        return rr.toJSONString();
    }

    @RequestMapping("/login2.do")
    public String login2(@RequestBody User user) {
//    public String login(@RequestBody @ModelAttribute User user) {//, @RequestParam("I_D") String id
        JSONObject rr = new JSONObject();
//        if (!(strCode.toUpperCase().equals(session.getAttribute("strCode")))) {
//            rr.put("state",0) ;
//            rr.put("message","验证码错误") ;
//            return rr.toJSONString();
//        }

        System.out.println(user);
        if ("".equals(user.getAddTime()) || "".equals(user.getPassword())) {
            rr.put("state", 0);
            rr.put("message", "用户名密码不可为空");
        } else {
            try {
                user = userService.login(user.getAdminName());
                rr.put("state", 1);
                rr.put("message", "登录成功");
                rr.put("name", user.getName());
//                session.setAttribute("user", user);
            } catch (Exception e) {
                e.printStackTrace();
                rr.put("state", 0);
                rr.put("message", e.getMessage());
            }
        }
        System.out.println(rr);
        return rr.toJSONString();
    }
}
