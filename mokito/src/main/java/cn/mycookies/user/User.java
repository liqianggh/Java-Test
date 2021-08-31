package cn.mycookies.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liqiang
 * @date 2021-06-24 下午8:46
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    /**
     * 唯一表示：id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
}
