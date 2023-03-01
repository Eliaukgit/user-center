package com.yupi.usercenter.exception;

import com.yupi.usercenter.common.ErrorCode;

/**
 * ClassName:BusinessException
 * Package :com.yupi.usercenter.exception
 * Description :
 *
 * @Author : coder_mu
 * @Create : 2023/2/25 - 12:46
 */
public class BusinessException extends RuntimeException {
    private final int code;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    private final String description;

    public BusinessException(String message, int code, String description) {
        super(message);
        this.code = code;
        this.description = description;
    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description=errorCode.getDescription();
    }
    public BusinessException(ErrorCode errorCode, String description) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.description = description;
    }
}
