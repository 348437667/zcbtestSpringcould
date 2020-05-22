package hk.zcb.userModule.controller;

import com.alibaba.fastjson.JSONObject;
import hk.zcb.userModule.dao.entity.User;
import hk.zcb.userModule.service.UserService;

import hk.zcb.userModule.util.RedisUtils;
import hk.zcb.userModule.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@RequestMapping("/user")
@RestController
public class UserController {

    public UserService userService;
    /**
     * redis工具类
     */
    private RedisUtils redisUtils;

    /**
     * service类
     *
     * @param userService
     */
    @Autowired
    public UserController(UserService userService, RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
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
                        @RequestParam(value = "password", defaultValue = "") String password, HttpSession session, String strCode) {
        JSONObject rr = new JSONObject();
        if (!(strCode.toUpperCase().equals(session.getAttribute("strCode")))) {
            rr.put("state", 0);
            rr.put("message", "验证码错误");
            return rr.toJSONString();
        }
        if ("".equals(adminName) || "".equals(password)) {
            rr.put("state", 0);
            rr.put("message", "用户名密码不可为空");
        } else {
            try {
                User user = userService.login(adminName);
                rr.put("state", 1);
                rr.put("message", "登录成功");
                rr.put("name", user.getName());
                String uuid = Utils.getUUID();
                boolean flag = redisUtils.set(uuid, user.toJsonString(), 30 * 60 * 1000L);
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
        JSONObject rr = new JSONObject();
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

    @RequestMapping("authCode")
    public void getAuthCode(HttpServletResponse response, HttpSession session) {
        int width = 63;
        int height = 37;
        Random random = new Random();
        // 设置response头信息
        // 禁止缓存
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 生成缓冲区image类
        BufferedImage image = new BufferedImage(width, height, 1);
        // 产生image类的Graphics用于绘制操作
        Graphics g = image.getGraphics();
        // Graphics类的样式
        g.setColor(Utils.getRandColor(200, 250));
        g.setFont(new Font("Times New Roman", 0, 28));
        g.fillRect(0, 0, width, height);
        // 绘制干扰线
        for (int i = 0; i < 40; i++) {
            g.setColor(Utils.getRandColor(130, 200));
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int x1 = random.nextInt(12);
            int y1 = random.nextInt(12);
            g.drawLine(x, y, x + x1, y + y1);
        }

        // 绘制字符
        String strCode = "";
        char c;
        for (int i = 0; i < 4; i++) {
            // 产生一个26以内的随机整数
            int number = random.nextInt(26);
            while (true) {
                // 如果生成的是偶数，则随机生成一个数字
                if (number % 2 == 0) {
                    c = (char) ('0' + (char) ((int) (Math.random() * 10)));
                    // 如果生成的是奇数，则随机生成一个字母
                } else {
                    c = (char) ((char) ((int) (Math.random() * 26)) + 'A');
                }
                if (c != '0' && c != 'O')
                    break;
            }
            String rand = String.valueOf(c);// random.nextInt(10)
            strCode = strCode + rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 6, 28);
        }
        // 将字符保存到session中用于前端的验证

        session.setAttribute("strCode", strCode);
        g.dispose();

        try {
            ImageIO.write(image, "JPEG", response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
