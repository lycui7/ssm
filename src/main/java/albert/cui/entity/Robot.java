package albert.cui.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author albert.cui
 * @date 2019/3/3 15:44
 */
@Getter
@Setter
public class Robot implements Serializable {
    private String id;
    private String username;
    private String password;
}
