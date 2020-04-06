package com.code.classsystem.shiro.realm;

import com.code.classsystem.entity.User;
import com.code.classsystem.service.UserService;
import com.code.classsystem.shiro.util.ShiroUtils;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.AuthenticationFailException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;




@Component
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(UserRealm.class);

    /**
     * 用户授权
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user = (User) principalCollection.getPrimaryPrincipal();
        String userName = user.getName();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        List<SAuRole> roleList = isAuRoleUserService.getRolesByUserName(userName);
//        for (SAuRole role : roleList) {
//            authorizationInfo.addRole(role.getRoleName());
//            List<String> funcs = isAuRoleFunctionService.getRoleFunctionUrlsByRoleID(role.getId());
//            authorizationInfo.addStringPermissions(funcs);
//        }
        return authorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        User user = userService.getByUserAccount(userName);

        if (user == null) {
            logger.error("用户名错误：{}", userName);
            throw new AuthenticationFailException(ErrorEnum.USER_NAME_ERROR.setMsg("用户名" + userName + "错误"));
        }
        String loginPassword = new String(token.getPassword());
        if (StringUtils.isEmpty(loginPassword) || !loginPassword.equals(user.getPassword())) {
            logger.error("登录密码错误：{}", token.getPassword());
            throw new AuthenticationFailException(ErrorEnum.USER_NAME_ERROR.setMsg("登录密码" + loginPassword + "错误"));
        }

        ShiroUtils.setSessionAttribute("user",user);

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
