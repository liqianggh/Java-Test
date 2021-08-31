package test.cn.mycookies.user;

import cn.mycookies.user.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author liqiang
 * @date 2021-07-01 下午3:15
 **/
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    // Fake：需要提前构造的假数据
    AddUserRequest fakeAddUserRequest;

    // Mock: mock外部依赖
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserDao userDao;
    @Mock
    private EmailService emailService;

    @Before
    public void init() {
        fakeAddUserRequest = new AddUserRequest("zhangsan", "zhangsan@163.com");
        when(userDao.insert(any())).thenReturn(1L);
        when(emailService.sendVerifyEmail(anyString())).thenReturn(true);
    }

    @Test
    public void testAddUser4NullParam() {
        // GIVEN
        fakeAddUserRequest = null;
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        assertEquals(addResult.getMsg(), "添加用户参数不能为空");
    }

    @Test
    public void testAddUser4BadEmail() {
        // GIVEN
        fakeAddUserRequest.setEmail(null);
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        assertEquals(addResult.getMsg(), "邮箱格式错误");
    }

    @Test
    public void testAddUser4BadUserName() {
        // GIVEN
        fakeAddUserRequest.setUserName(null);
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        assertEquals(addResult.getMsg(), "用户名不能为空");
    }

    @Test
    public void testAddUser4DbError() {
        // GIVEN
        when(userDao.insert(any())).thenReturn(-1L);
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        assertEquals(addResult.getMsg(), "添加用户失败，请善后重试");
    }

    @Test
    public void testAddUser4SendEmail() {
        // GIVEN
        // WHEN
        ResultDTO<Long> addResult = userService.addUser(fakeAddUserRequest);
        // THEN
        assertTrue(addResult.isSuccess());
        verify(emailService, times(1)).sendVerifyEmail(any());
        verify(emailService).sendVerifyEmail(fakeAddUserRequest.getEmail());
    }

}

