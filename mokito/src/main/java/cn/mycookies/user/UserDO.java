package cn.mycookies.user;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author liqiang
 * @date 2021-07-01 下午3:22
 **/
@Data
public class UserDO {
    /**
     * 用户名
     */
    private String userName;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
