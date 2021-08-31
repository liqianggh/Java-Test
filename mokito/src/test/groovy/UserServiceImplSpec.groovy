import cn.mycookies.user.AddUserRequest
import cn.mycookies.user.EmailService
import cn.mycookies.user.UserDao
import cn.mycookies.user.UserServiceImpl
import spock.lang.Specification

class UserServiceImplSpec extends Specification {
    UserServiceImpl userService = new UserServiceImpl();
    AddUserRequest fakeAddUserRequest;
    def userDao = Mock(UserDao)
    def emailService = Mock(EmailService)

    def setup() {
        // Fake数据创建
        fakeAddUserRequest = new AddUserRequest(userName: "zhangsan", email: "zhangsan@163.com")
        // 注入Mock对象
        userService.userDao = userDao
        userService.emailService = emailService
    }

    def "testAddUser4BadParam"() {
        given:
        if (Objects.isNull(userName) || Objects.is(email)) {
            fakeAddUserRequest = null
        } else {
            fakeAddUserRequest.setUserName(userName)
            fakeAddUserRequest.setEmail(email)
        }
        when:
        def result = userService.addUser(fakeAddUserRequest)
        then:
        Objects.equals(result.getMsg(), resultMsg)
        where:
        userName   | email              | resultMsg
        null       | null               | "添加用户参数不能为空"
        "Java填坑笔记" | null               | "邮箱格式错误"
        null       | "javaTKBJ@163.com" | "用户名不能为空"
    }

    def "testAddUser4DbError"() {
        given:
        _ * userDao.insert(_) >> -1L
        when:
        def result = userService.addUser(fakeAddUserRequest)
        then:
        Objects.equals(result.getMsg(), "添加用户失败，请稍后重试")
    }

    def "testAddUser4SendEmail"() {
        given:
        _ * userDao.insert() >> 1
        when:
        def result = userService.addUser(fakeAddUserRequest)
        then:
        result.isSuccess()
        1 * emailService.sendVerifyEmail(fakeAddUserRequest.getEmail())
    }
}
