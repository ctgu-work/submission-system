package com.ctgu.contributionsystem.shiro;


import com.ctgu.contributionsystem.model.Admin;
import com.ctgu.contributionsystem.model.User;
import com.ctgu.contributionsystem.service.AdminService;
import com.ctgu.contributionsystem.utils.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * AdminRealm
 *
 * @author chase
 * @date 2019/12/24 0024
 */
public class AdminRealm extends AuthorizingRealm {

    @Autowired
    AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();
        // 解密获得phoneNumber，用于和数据库进行对比
        String phoneNumber = JwtUtil.getPhoneNumber(token);
        if (phoneNumber == null) {
            throw new AuthenticationException("token无效");
        }
        Admin admin = adminService.findByPhoneNumber(phoneNumber);
        if (admin == null) {
            throw new AuthenticationException("用户不存在!");
        }
        if (!JwtUtil.verify(token, phoneNumber, admin.getPassword())) {
            throw new AuthenticationException("用户名或密码错误");
        }

        return new SimpleAuthenticationInfo(token, token, getName());
    }
}
