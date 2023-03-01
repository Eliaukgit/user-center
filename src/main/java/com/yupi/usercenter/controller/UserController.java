package com.yupi.usercenter.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yupi.usercenter.common.BaseResponse;
import com.yupi.usercenter.common.ErrorCode;
import com.yupi.usercenter.common.ResultUtil;
import com.yupi.usercenter.exception.BusinessException;
import com.yupi.usercenter.model.domain.User;
import com.yupi.usercenter.model.domain.request.UserLoginRequest;
import com.yupi.usercenter.model.domain.request.UserRegisterRequest;
import com.yupi.usercenter.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

import static com.yupi.usercenter.constant.UserConstant.ADMIN_ROLE;
import static com.yupi.usercenter.constant.UserConstant.USER_LOGIN_STATE;

/**
 * ClassName:UserController
 * Package :com.yupi.usercenter.controller
 * Description :
 *
 * @Author : coder_mu
 * @Create : 2023/1/11 - 下午 21:46
 */

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    //打上⼀个注解@RequestBody，没打上的话，Springmvc框架不知道怎么把  前端传来  的json参数去合适的对象做⼀个关联
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode = userRegisterRequest.getPlanetCode();
        //controller 层倾向于对请求参数本身的校验，不涉及业务逻辑本身
        //service 层是对业务逻辑的校验（有可能被 controller 之外的类调用
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword, planetCode);
        return ResultUtil.success(result);
    }

    @PostMapping("/login")
    //打上⼀个注解@RequestBody，没打上的话，Springmvc框架不知道怎么把  前端传来  的json参数去合适的对象做⼀个关联
    public BaseResponse<User> userLogin(@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if (userLoginRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        //controller 层倾向于对请求参数本身的校验，不涉及业务逻辑本身
        //service 层是对业务逻辑的校验（有可能被 controller 之外的类调用
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.userLogin(userAccount, userPassword, request);
        return ResultUtil.success(user);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        int result = userService.userLogout(request);
        return ResultUtil.success(result);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN);
        }
        long userId = currentUser.getId();
        //todo 校验用户是否合法
        User user = userService.getById(userId);
        User safeUser = userService.getSafetyUser(user);
        return ResultUtil.success(safeUser);
    }

    @GetMapping("/search")
    public BaseResponse<List<User>> searchUsers(String username, HttpServletRequest request) {
        if (isAdmin(request)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            //先判断这个查询用户是否存在，如果不存在就进行模糊查询，直接写username是在username前后加%进行模糊查询
            queryWrapper.like("username", username);
        }
        List<User> userList = userService.list(queryWrapper);
/*        return userList.stream().map(user -> {
            return userService.getSafetyUser(user);
        }).collect(Collectors.toList());*/
        List<User> list = userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());//简化版
        return ResultUtil.success(list);
    }

    //不建议使用@DeleteMapping 如果使用这个后面的路径就可以不写，功能不够明确，但是也可以这样使用，看实际要求的规范吧
    @PostMapping("/delete")//如果作为泛型必须写成对象Boolean
    public BaseResponse<Boolean> deleteUsers(@RequestBody Long id, HttpServletRequest request) {
        if (isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH);
        }
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        //增加注解后调用BaseMapper的deleteById(id)或者IService的removeById(id)，是逻辑删除。如果没有增加该注解，是真删除
        boolean b = userService.removeById(id);
        return ResultUtil.success(b);
    }

    /**
     * 是否为管理员
     * 写这个方法是为了减少代码的重复性，把重复的代码提取出来
     *
     * @param request
     * @return true falase
     */
    private boolean isAdmin(HttpServletRequest request) {
        //仅管理员可以查询用户 得到前端发来的包装
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);//通过类来拿变量，不能通过userService对象来拿
        User user = (User) userObj;
/*        if (user == null || user.getUserRole() != ADMIN_ROLE) {
            return false;
        }*/
        return user == null || user.getUserRole() != ADMIN_ROLE;
    }
}
