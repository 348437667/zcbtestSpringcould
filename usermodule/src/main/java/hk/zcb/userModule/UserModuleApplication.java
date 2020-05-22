package hk.zcb.userModule;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication//启动类标志
//@ComponentScan(basePackages = { "hk.zcb.userModule.controller", "hk.zcb.userModule.service" })
@MapperScan("hk.zcb.userModule.dao.mapper")//扫描mapper类的所在的包，让mybatis找到
public class UserModuleApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserModuleApplication.class, args);
    }

}
