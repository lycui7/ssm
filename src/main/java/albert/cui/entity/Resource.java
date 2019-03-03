package albert.cui.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author albert.cui
 * @date 2019/3/3 14:49
 */
@Getter
@Setter
public class Resource implements Serializable {
    private String id;
    /**
     * 访问的URL
     */
    private String value;
    /**
     * 存在什么条件才能访问该URL
     */
    private String permission;
}
