package test.cn.mycookies.user;

import cn.mycookies.user.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author liqiang
 * @date 2021-07-01 下午3:15
 **/
public class UserServiceImplStubTest {
    // Fake：需要提前构造的假数据
    AddUserRequest fakeAddUserRequest;

    private UserServiceImpl userService;
    private UserDaoStub userDaoStub;
    private EmailServiceStub emailServiceStub;

    @Before
    public void init() {
        fakeAddUserRequest = new AddUserRequest("zhangsan", "zhangsan@163.com");
        userDaoStub = new UserDaoStub();
        emailServiceStub = new EmailServiceStub();
        userService = new UserServiceImpl(userDaoStub, emailServiceStub);
    }

    @Test
    public void testAddUser4NullParam() {
        // GIVEN
        fakeAddUserRequest = null;
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        Assert.assertEquals(addResult.getMsg(), "添加用户参数不能为空");
    }

    @Test
    public void testAddUser4BadEmail() {
        // GIVEN
        fakeAddUserRequest.setEmail(null);
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        Assert.assertEquals(addResult.getMsg(), "邮箱格式错误");
    }

    @Test
    public void testAddUser4BadUserName() {
        // GIVEN
        fakeAddUserRequest.setUserName(null);
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        Assert.assertEquals(addResult.getMsg(), "用户名不能为空");
    }

    @Test
    public void testAddUser4DbError() {
        // GIVEN
        fakeAddUserRequest.setUserName("badcase");
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        Assert.assertEquals(addResult.getMsg(), "添加用户失败，请稍后重试");
    }

    @Test
    public void testAddUser4SendEmail() {
        // GIVEN
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        Assert.assertTrue(addResult.isSuccess());
        Assert.assertEquals(emailServiceStub.invokeCount, 1);
    }

}

