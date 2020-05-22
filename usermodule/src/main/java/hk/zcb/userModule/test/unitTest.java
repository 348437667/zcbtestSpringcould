package hk.zcb.userModule.test;

import hk.zcb.userModule.dao.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class unitTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test4getUser(){
        System.out.println(userMapper.getUserByUserName("zcb"));
    }
}
