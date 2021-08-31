package cn.mycookies.user;

/**
 * @author liqiang
 * @date 2021-06-24 下午8:45
 **/
public interface UserDao {

    long insert(UserDO user);

    UserDO getById(Long id);
}
