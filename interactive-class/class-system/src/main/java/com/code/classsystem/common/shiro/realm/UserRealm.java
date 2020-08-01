package com.code.classsystem.common.shiro.realm;

import com.code.classsystem.entity.User;
import com.code.classsystem.service.UserService;
import com.code.classsystem.common.shiro.util.ShiroUtils;
import com.code.classsystem.util.DateUtils;
import com.code.classsystem.util.IPUtil;
import com.code.classsystem.vo.UserInfoVo;
import com.code.core.enums.ErrorEnum;
import com.code.core.exception.AuthenticationFailException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
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
        String userId = ShiroUtils.getUserId();
        UserInfoVo userInfoVo = userService.getUserInfoById(userId);
        String roleCode = userInfoVo.getRoleCode();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole(roleCode);
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
            throw new AuthenticationFailException(ErrorEnum.USER_NAME_ERROR.setMsg("用户名" + userName + "不存在"));
        }
        String loginPassword = new String(token.getPassword());
        if (StringUtils.isEmpty(loginPassword) || !loginPassword.equals(user.getPassword())) {
            logger.error("登录密码错误：{}", token.getPassword());
            throw new AuthenticationFailException(ErrorEnum.USER_NAME_ERROR.setMsg("登录密码" + loginPassword + "错误"));
        }
        //用户登录成功后更新用户信
        updateLoginUser(user);
        ShiroUtils.setSessionAttribute("user", user);

        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    private void updateLoginUser(User user) {
        user.setLoginIp(IPUtil.getIp());
        user.setLoginTime(DateUtils.getCurTimeStr());
        userService.updateById(user);
    }
}
