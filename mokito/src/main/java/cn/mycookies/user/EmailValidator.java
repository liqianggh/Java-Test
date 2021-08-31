package cn.mycookies.user;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liqiang
 * @date 2021-07-01 下午2:50
 **/
public class EmailValidator {

    public static boolean validate(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        return true;
    }
}
