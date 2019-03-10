package albert.cui.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * 响应的json对象类
 * @author albert.cui
 * @date 2019/3/10 22:49
 */
@Getter
@Setter
public class BaseResult {
    private String msg;
    private String status;
}
