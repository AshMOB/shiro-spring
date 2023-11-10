package com.example.shirospring;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Object principal = principalCollection.getPrimaryPrincipal();
// Use the principal object to get the user's information.
        Set<String> roles = new HashSet<>();
        roles.add("administrator");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        info.addStringPermission("manage");
        return info;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        boolean account = "zhangsan".equals(token.getUsername());
        if(account){
            return new SimpleAuthenticationInfo("zhangsan","123",getName());
        }
        return null;
    }
}
