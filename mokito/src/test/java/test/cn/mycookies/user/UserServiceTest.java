//package test.cn.mycookies.user;
//
//import cn.mycookies.user.AddUserRequest;
//import cn.mycookies.user.ResultDTO;
//import cn.mycookies.user.UserService;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//
///**
// * @author liqiang
// * @date 2021-07-06 下午7:56
// **/
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationLoader.class)
//public class UserServiceTest {
//    @Autowired
//    private UserService userService;
//    @Test
//    public void testAddUser() {
//        AddUserRequest addUserRequest = new AddUserRequest("zhangsan", "zhangsan@163.com");
//        ResultDTO<Long> addResult = userService.addUser(addUserRequest);
//        System.out.println(addResult);
//    }
//}
