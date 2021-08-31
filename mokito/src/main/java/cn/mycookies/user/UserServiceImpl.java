package cn.mycookies.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author liqiang
 * @date 2021-06-24 下午8:42
 **/
@AllArgsConstructor
@NoArgsConstructor
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    private EmailService emailService;

    @Override
    public ResultDTO<Long> addUser(AddUserRequest request) {
        // 1. 校验参数
        ResultDTO<Void> validateResult = validateAddUserParam(request);
        if (!validateResult.isSuccess()) {
            return ResultDTO.paramError(validateResult.getMsg());
        }

        // 2. 添加用户
        UserDO userDO = request.buildUserDO();
        long id = userDao.insert(userDO);

        // 3. 添加成功，返回验证激活邮件
        if (id > 0) {
            emailService.sendVerifyEmail(request.getEmail());
            return ResultDTO.success(id);
        }
        return ResultDTO.internalError("添加用户失败，请稍后重试");
    }

    /**
     * 校验添加用户参数
     */
    private ResultDTO<Void> validateAddUserParam(AddUserRequest request) {
        if (Objects.isNull(request)) {
            return ResultDTO.paramError("添加用户参数不能为空");
        }
        if (StringUtils.isBlank(request.getUserName())) {
            return ResultDTO.paramError("用户名不能为空");
        }
        if (!EmailValidator.validate(request.getEmail())) {
            return ResultDTO.paramError("邮箱格式错误");
        }
        return ResultDTO.success();
    }
}
