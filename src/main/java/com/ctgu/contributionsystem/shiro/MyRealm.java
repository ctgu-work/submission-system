package com.ctgu.contributionsystem.shiro;

import com.ctgu.contributionsystem.dto.JwtToken;
import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.AdminService;
import com.ctgu.contributionsystem.service.UserService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * @Description TODO
 * @Author wh_lan
 * @create 2019-12-18 17:02
 * @ClassName MyRealm
 * @Version 1.0.0
 */
@Component
public class MyRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;


    /**
     * 必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @Author wh
     * @Description 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * @Date 2019/12/18 17:05
     * @Param [principals]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String phoneNumber = JwtUtil.getPhoneNumber(principals.toString());
        User user = userService.findByPhoneNumber(phoneNumber);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得phoneNumber，用于和数据库进行对比
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if (phoneNumber == null) {
            throw new AuthenticationException("token无效");
        }
        User user = userService.findByPhoneNumber(phoneNumber);
        Admin admin = adminService.findByPhoneNumber(phoneNumber);
        if (user == null) {
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, phoneNumber, user.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, "my_realm");
    }
}

