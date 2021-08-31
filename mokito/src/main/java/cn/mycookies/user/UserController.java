package cn.mycookies.user;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 用户controller
 *
 * @author liqiang
 * @date 2021-06-24 下午8:43
 **/
public class UserController {

    @Autowired
    private UserServiceImpl userServiceImpl;

}
