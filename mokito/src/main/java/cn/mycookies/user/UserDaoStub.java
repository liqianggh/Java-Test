package cn.mycookies.user;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author liqiang
 * @date 2021-07-01 下午4:30
 **/
public class UserDaoStub implements UserDao {
    UserDO fakeUser = new UserDO();

    {
        fakeUser.setUserName("zhangsan");
        fakeUser.setEmail("zhangsan@163.com");
        LocalDateTime dateTime = LocalDateTime.of(2021, 7, 1, 12, 30, 0);
        fakeUser.setCreateTime(dateTime);
        fakeUser.setUpdateTime(dateTime);
    }

    @Override
    public UserDO getById(Long id) {
        if (Objects.isNull(id) || id <= 0) {
            return new UserDO();
        }
        return fakeUser;
    }

    @Override
    public long insert(UserDO user) {
        if (Objects.isNull(user)) {
            return -1;
        }
        if (Objects.equals(user.getUserName(), "badcase")) {
            return -1;
        }
        return 1;
    }

}
