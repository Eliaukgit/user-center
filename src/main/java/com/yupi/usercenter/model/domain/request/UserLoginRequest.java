package com.yupi.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * ClassName:UserLoginRequest
 * Package :com.yupi.usercenter.model.domain.request
 * Description :
 *
 * @Author : coder_mu
 * @Create : 2023/1/11 - 下午 23:09
 */
@Data
public class UserLoginRequest implements Serializable {
    //serialVersionUID防止序列化过程中冲突
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;
}
