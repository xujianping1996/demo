package com.jianping.demo.util.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.jianping.demo.entity.User;
import com.jianping.demo.service.UserService;

public class MyRealm extends AuthenticatingRealm {
	private UserService userService;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private void setUserMapper(UserService userService) {
        this.userService = userService;
    }

	/**
	 * 其中 doGetAuthorizationInfo 方法只有在需要权限认证时才会进去，比如前面配置类中配置了
	 * filterChainDefinitionMap.put("/admin/**", "roles[admin]"); 的管理员角色，这时进入 /admin
	 * 时就会进入 doGetAuthorizationInfo 方法来检查权限；而 doGetAuthenticationInfo
	 * 方法则是需要身份认证时（比如前面的 Subject.login() 方法）才会进入
	 */
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
//		System.out.println("————权限认证————");
		logger.info("正在进行权限权限认证。。。");
		String username = (String) SecurityUtils.getSubject().getPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 获得该用户角色
//		String role = userMapper.getRole(username);
//		Set<String> set = new HashSet<>();
//		// 需要将 role 封装到 Set 作为 info.setRoles() 的参数
//		set.add(role);
//		// 设置该用户拥有的角色
//		info.setRoles(set);
		return info;
	}
//	<!-- 身份认证异常 -->  
//	<!-- 身份令牌异常，不支持的身份令牌 -->  
//	org.apache.shiro.authc.pam.UnsupportedTokenException  
//	<!-- 未知账户/没找到帐号,登录失败 -->  
//	org.apache.shiro.authc.UnknownAccountException  
//	<!-- 帐号锁定 -->  
//	org.apache.shiro.authc.LockedAccountException  
//	<!-- 用户禁用 -->  
//	org.apache.shiro.authc.DisabledAccountException  
//	<!-- 登录重试次数，超限。只允许在一段时间内允许有一定数量的认证尝试 -->  
//	org.apache.shiro.authc.ExcessiveAttemptsException  
//	<!-- 一个用户多次登录异常：不允许多次登录，只能登录一次 。即不允许多处登录-->  
//	org.apache.shiro.authc.ConcurrentAccessException  
//	<!-- 账户异常 -->  
//	org.apache.shiro.authc.AccountException  
//	<!-- 过期的凭据异常 -->  
//	org.apache.shiro.authc.ExpiredCredentialsException  
//	<!-- 错误的凭据异常 -->  
//	org.apache.shiro.authc.IncorrectCredentialsException  
//	<!-- 凭据异常 -->  
//	org.apache.shiro.authc.CredentialsException  
//	org.apache.shiro.authc.AuthenticationException  
//	<!-- 权限异常 -->  
//	<!-- 没有访问权限，访问异常 -->  
//	org.apache.shiro.authz.HostUnauthorizedException  
//	org.apache.shiro.authz.UnauthorizedException  
//	<!-- 授权异常 -->  
//	org.apache.shiro.authz.UnauthenticatedException  
//	org.apache.shiro.authz.AuthorizationException  
//	<!-- shiro全局异常 -->  
//	org.apache.shiro.ShiroException
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
		// TODO Auto-generated method stub
		System.out.println("————身份认证方法————");
		UsernamePasswordToken myToken = (UsernamePasswordToken) token;
		// 从数据库获取对应用户名密码的用户
		User user = userService.selectUserByLoginName(myToken.getUsername());
		if (user == null) {
			throw new UnknownAccountException();//查询不到当前用户，用户名不存在
		}
		if (1==user.getLockState()) {
			throw new DisabledAccountException();//用户锁定
		}
//		return new SimpleAuthenticationInfo(token.getPrincipal(), password, getName());
		ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
		return new SimpleAuthenticationInfo(user, user.getPassWord(), credentialsSalt, getName());
	}

}
