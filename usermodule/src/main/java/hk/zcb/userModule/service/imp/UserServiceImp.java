package hk.zcb.userModule.service.imp;

import hk.zcb.userModule.dao.entity.User;
import hk.zcb.userModule.dao.mapper.UserMapper;
import hk.zcb.userModule.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public void t1est() {
        System.out.println("laile");
    }

    @Override
    public User login(String adminName) {
        return userMapper.getUserByUserName(adminName);
    }
}
