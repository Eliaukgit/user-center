package com.yupi.usercenter.model.domain.request;

import lombok.Data;
import java.io.Serializable;

/**
 * ClassName:UserRegisterRequest
 * Package :com.yupi.usercenter.model.domain.request
 * Description :
 *
 * @Author : coder_mu
 * @Create : 2023/1/11 - 下午 22:08
 */
@Data
public class UserRegisterRequest implements Serializable {
    //serialVersionUID防止序列化过程中冲突
    private static final long serialVersionUID = 3191241716373120793L;
    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;
}
