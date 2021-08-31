package cn.mycookies.user;

/**
 * @author liqiang
 * @date 2021-07-01 下午2:40
 **/
public interface EmailService {
    boolean sendVerifyEmail(String email);
}
