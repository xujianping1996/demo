package com.jianping.demo.util.shiro;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianping.demo.dao.UserMapper;

public class MyRealm extends AuthorizingRealm {
	private UserMapper userMapper;

    @Autowired
    private void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

	/**
	 * 其中 doGetAuthorizationInfo 方法只有在需要权限认证时才会进去，比如前面配置类中配置了
	 * filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，这时进入 /admin
	 * 时就会进入 doGetAuthorizationInfo 方法来检查权限；而 doGetAuthenticationInfo
	 * 方法则是需要身份认证时（比如前面的 Subject.login() 方法）才会进入
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		System.out.println("————权限认证————");
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获得该用户角色
		String role = userMapper.getRole(username);
		Set<String> set = new HashSet<>();
		// 需要将 role 封装到 Set 作为 info.setRoles() 的参数
		set.add(role);
		// 设置该用户拥有的角色
		info.setRoles(set);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("————身份认证方法————");
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		// 从数据库获取对应用户名密码的用户
		String password = userMapper.getUserPassword(myToken.getUsername());
		if (null == password) {
			throw new AccountException("用户名不正确");
		} else if (!password.equals(new String((char[]) myToken.getCredentials()))) {
			throw new AccountException("密码不正确");
		}
		return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
	}

}
