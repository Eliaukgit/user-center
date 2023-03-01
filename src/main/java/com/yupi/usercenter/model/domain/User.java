package com.yupi.usercenter.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName user
 */
@TableName(value ="user")
@Data
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    //long的默认值是0；Long默认值是null； long是基础数据类型，Long是long的封装类型，也叫包装类；
    //基本类型：long,int,byte,float,double,char,short,boolean

    /**
     * ⽤户昵称
     */
    private String username;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * ⽤户头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0-正常
     */
    private Integer userStatus;
    /**
     * 角色 0-普通用户 1-管理员
     */
    private Integer userRole;
    /**
     * 电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;
    /**
     * 星球编号
     */
    @TableField(value = "planetCode")
    private String planetCode;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}