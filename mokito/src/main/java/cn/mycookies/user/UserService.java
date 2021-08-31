package cn.mycookies.user;

/**
 * 用户接口定义
 *
 * @author liqiang
 * @date 2021-06-24 下午8:44
 **/
public interface UserService {
    ResultDTO<Long> addUser(AddUserRequest request);
}
