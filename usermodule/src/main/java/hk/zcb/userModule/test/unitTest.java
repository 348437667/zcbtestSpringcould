package hk.zcb.userModule.test;

import hk.zcb.userModule.dao.mapper.UserMapper;
import hk.zcb.userModule.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class unitTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void test4getUser(){
        System.out.println(userMapper.getUserByUserName("zcb"));
    }
    @Resource
    private RedisUtils redisUtils;

    /**
     * 插入缓存数据
     */
    @Test
    public void set() {
        redisUtils.set("redis_key", "123");
    }

    /**
     * 读取缓存数据
     */
    @Test
    public void get() {
        String value = redisUtils.get("redis_key");
        System.out.println(value);
    }
    /**
     * 读取缓存数据
     */
    @Test
    public void delete() {
        boolean value = redisUtils.delete("redis_key");
        System.out.println(value);
    }
}
