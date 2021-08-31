package cn.mycookies.user;

/**
 * @author liqiang
 * @date 2021-07-01 下午4:32
 **/
public class EmailServiceStub implements EmailService {
    public int invokeCount = 0;

    @Override
    public boolean sendVerifyEmail(String email) {
        invokeCount++;
        // do something
        return true;
    }
}
