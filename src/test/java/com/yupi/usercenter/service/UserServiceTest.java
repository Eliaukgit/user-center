package com.yupi.usercenter.service;

import com.yupi.usercenter.model.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * ClassName:UserServiceTest
 * Package :com.yupi.usercenter.service
 * Description :
 *
 * @Author : coder_mu
 * @Create : 2023/1/9 - 下午 21:14
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;

    @Test
    void listByIds() {
        User user = new User();
        user.setUsername("dogYuPi");
        user.setUserAccount("123");
        user.setAvatarUrl("https://profile.csdnimg.cn/F/B/6/1_weixin_51470901");
        user.setGender(1);
        user.setUserPassword("123");
        user.setEmail("3131917861@qq.com");
        user.setUserStatus(0);
        user.setPhone("13673770387");
//        boolean result = userService.save(user);
        System.out.println(user.getId());
        //断言
//        Assertions.assertTrue(result);
    }

    @Test
    void userRegister() {
        String userAccount = "yupi";
        String userPassword = "";
        String checkPassword = "123456";
        String plametCode = "1";
//        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        /*Assertions.assertEquals(-1, result);//添加断言来验证我们的预测

        userAccount = "yu";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yupi";
        userPassword = "123456";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        userAccount = "yu pi";
        userPassword = "12345678";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);
        checkPassword = "123456789";
        result = userService.userRegister(userAccount, userPassword, checkPassword);
        Assertions.assertEquals(-1, result);*/
        userAccount = "yupidog";
        userPassword = "12345678";
        checkPassword = "12345678";
        long result = userService.userRegister(userAccount, userPassword, checkPassword,plametCode);
        System.out.println(result);
//        Assertions.assertEquals(-1, result);
//        userAccount = "yupi2";
//        result = userService.userRegister(userAccount, userPassword, checkPassword);
//        Assertions.assertEquals(-1, result);

    }
}