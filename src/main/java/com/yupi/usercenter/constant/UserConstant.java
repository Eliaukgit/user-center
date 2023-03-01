package com.yupi.usercenter.constant;

/**
 * ClassName:UserConstant
 * Package :com.yupi.usercenter.constant
 * Description : 用户常量
 *
 * @Author : coder_mu
 * @Create : 2023/1/13 - 上午 11:34
 */
public interface UserConstant {
    /**
     * 用户登录状态键  写在接口里可以让实现类和controller都可以使用这个，controller层需要拿到session里的鉴权字段来鉴别是否为管理员
     */
    String USER_LOGIN_STATE = "userLoginState";
    /**
     * 权限 默认为0用户  1为管理员
     */
    int DEFAULT_ROLE = 0;
    int ADMIN_ROLE = 1;
}
