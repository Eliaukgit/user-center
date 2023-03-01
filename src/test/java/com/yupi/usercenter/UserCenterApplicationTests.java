package com.yupi.usercenter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.security.NoSuchAlgorithmException;

@SpringBootTest
//@RunWith(SpringRunner.class)
class UserCenterApplicationTests {
    @Test
    void testDigest() throws NoSuchAlgorithmException {
        //前面的abcd是给密码加盐，后面的mypassword是明文密码
        String newPassword = DigestUtils.md5DigestAsHex(("abcd" + "mypassword").getBytes());
        System.out.println(newPassword);
    }
}
