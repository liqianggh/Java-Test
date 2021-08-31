package cn.mycookies.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author liqiang
 * @date 2021-07-01 下午3:15
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    public UserDO buildUserDO() {
        UserDO user = new UserDO();
        user.setEmail(email);
        user.setUserName(userName);

        LocalDateTime now = LocalDateTime.now();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        return user;
    }
}
