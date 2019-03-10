package albert.cui.shiro;

import albert.cui.entity.Resource;
import albert.cui.entity.Robot;
import albert.cui.mapper.RobotDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.security.auth.login.AccountNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Shiro数据库认证和授权
 * @author albert.cui
 * @date 2019/3/3 16:31
 */
public class ShiroDataBaseRealm extends AuthorizingRealm {
    //注入数据库访问类
    @Autowired
    private RobotDao robotDao;
    /**
     * 访问权限控制方法
     * 当用户进行访问链接时的授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        if(principalCollection == null) {
            throw new AuthorizationException("Principal对象不能为空");
        }
        Robot robot = (Robot) principalCollection.fromRealm(getName()).iterator().next();
        //获取用户响应的permission
        List<Resource> resourceList = robotDao.getResourcesForRobot(robot.getId());
        List<String> permissions = new ArrayList<>();
        for(Resource resource : resourceList) {
            permissions.add(resource.getPermission());
        }
        //创建授权信息对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //todo 现在授权信息对象有permission,但是没有动态获取role.应从数据库查出用户对应的角色。
        info.addRole("admin");
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 认证/登录方法
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        if(username == null) {
            throw new AccountException("用户名不能为空");
        }
        Robot robot = robotDao.getRobotByName(username);
        if(robot == null) {
            throw new UnknownAccountException("用户不存在");
        }
        //用户名通过认证，继续校验
        /**
         * Object principal：签名，程序可以在任意位置获取存入的对象
         * Object credentials：从数据库查出来的密码
         *  String realmName 当前realm的名称
         */
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(robot,robot.getPassword(),getName());
        return info;
    }
}
