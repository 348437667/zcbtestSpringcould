package hk.zcb.userModule.dao.mapper;

import hk.zcb.userModule.dao.entity.User;
import org.springframework.stereotype.Component;

@Component//存在是为了欺骗idea，防止代码编写时自动注入报错（虽然报错但是能正常运行），因为Autowired是springboot方法，加了component算是springboot组件
public interface UserMapper {
    /**
     * 根据用户名，获取用户具体数据
     * zcb
     *
     * @param adminName 用户名
     * @return 用户类
     */
    public User getUserByUserName(String adminName);


}
