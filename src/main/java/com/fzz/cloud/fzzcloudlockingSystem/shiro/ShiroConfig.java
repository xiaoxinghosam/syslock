//package com.fzz.cloud.fzzcloudlockingSystem.shiro;
//
//import java.util.LinkedHashMap;
//import java.util.Map;
//import javax.servlet.Filter;
//
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
//import org.crazycake.shiro.RedisCacheManager;
//import org.crazycake.shiro.RedisManager;
//import org.crazycake.shiro.RedisSessionDAO;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.filter.DelegatingFilterProxy;
//import com.xiaoleilu.hutool.lang.Base64;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//@Configuration
//public class ShiroConfig {
//
//	
//	private String host = "120.79.15.123";
//
//	private int port = 6379;
//
//	private int timeout = 0;
//
//	private static final Logger log = LoggerFactory.getLogger(ShiroFilterFactoryBean.class);
//	
//	/**
//	 * 注册DelegatingFilterProxy（Shiro）
//	 * 
//	 * @return
//	 */
//	@Bean
//	public FilterRegistrationBean filterRegistrationBean() {
//		FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//		filterRegistration.setFilter(new DelegatingFilterProxy("shiroFilter"));
//		// 该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//		filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//		filterRegistration.setEnabled(true);
//		filterRegistration.addUrlPatterns("/*");
//
//		return filterRegistration;
//	}
//	
//	
//	
//	/**
//	 * ShiroFilterFactoryBean 处理拦截资源文件问题。
//	 * 注意：单独一个ShiroFilterFactoryBean配置是或报错的，以为在
//	 * 初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
//	 *
//	 * Filter Chain定义说明 1、一个URL可以配置多个Filter，使用逗号分隔 2、当设置多个过滤器时，全部验证通过，才视为通过
//	 * 3、部分过滤器可指定参数，如perms，roles
//	 *
//	 */
//
//	@Bean("shiroFilter")
//	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
//		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//
//		// 必须设置 SecurityManager
//		shiroFilterFactoryBean.setSecurityManager(securityManager);
//
//		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
//		shiroFilterFactoryBean.setLoginUrl("/login/loginFirst");
//		// 登录成功后要跳转的链接
//		//shiroFilterFactoryBean.setSuccessUrl("/index");
//		// 未授权界面;
//		shiroFilterFactoryBean.setUnauthorizedUrl("/login/noRights");
//
//		// 自定义拦截器
//		Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
//		// 限制同一帐号同时在线的个数。
//		filtersMap.put("kickout", kickoutSessionControlFilter());
//		shiroFilterFactoryBean.setFilters(filtersMap);
//
//		// 权限控制map.
//		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
//		filterChainDefinitionMap.put("/login/loginFirst", "anon");
//		filterChainDefinitionMap.put("/login", "anon");
//		filterChainDefinitionMap.put("/logon", "anon");
//		// 配置不会被拦截的链接 顺序判断
//		// 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
//		// 从数据库获取动态的权限
//		// filterChainDefinitionMap.put("/add", "perms[权限添加]");
//		// <!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
//		// <!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
//		// logout这个拦截器是shiro已经实现好了的。
//		// 从数据库获取
//		/*
//		 * List<SysPermissionInit> list = sysPermissionInitService.selectAll();
//		 * 
//		 * for (SysPermissionInit sysPermissionInit : list) {
//		 * filterChainDefinitionMap.put(sysPermissionInit.getUrl(),
//		 * sysPermissionInit.getPermissionInit()); }
//		 */
//		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//		return shiroFilterFactoryBean;
//	}
//
//	
//
//	 @Bean(name="securityManager")
//	public SecurityManager securityManager() {
//		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//		// 设置realm.
//		securityManager.setRealm(myShiroRealm());
//		// 自定义缓存实现 使用redis
//		securityManager.setCacheManager(cacheManager());
//		// 自定义session管理 使用redis
//		securityManager.setSessionManager(sessionManager());
//		// 注入记住我管理器;
//		securityManager.setRememberMeManager(rememberMeManager());
//		return securityManager;
//	}
//
//	/**
//	 * 身份认证realm; (这个需要自己写，账号密码校验；权限等)
//	 * 
//	 * @return
//	 */
//	 @Bean(name="authRealm")
//	public MyShiroRealm myShiroRealm() {
//		MyShiroRealm myShiroRealm = new MyShiroRealm();
//		return myShiroRealm;
//	}
//
//	/**
//	 * 配置shiro redisManager 使用的是shiro-redis开源插件
//	 * 
//	 * @return
//	 */
//	public RedisManager redisManager() {
//		RedisManager redisManager = new RedisManager();
//		redisManager.setHost(host);
//		redisManager.setPort(port);
//		redisManager.setExpire(1800);// 配置缓存过期时间
//		redisManager.setTimeout(timeout);
//		// redisManager.setPassword(password);
//		return redisManager;
//	}
//
//	/**
//	 * cacheManager 缓存 redis实现 使用的是shiro-redis开源插件
//	 * 
//	 * @return
//	 */
//	public RedisCacheManager cacheManager() {
//		RedisCacheManager redisCacheManager = new RedisCacheManager();
//		redisCacheManager.setRedisManager(redisManager());
//		return redisCacheManager;
//	}
//
//	/**
//	 * RedisSessionDAO shiro sessionDao层的实现 通过redis 使用的是shiro-redis开源插件
//	 */
//	@Bean
//	public RedisSessionDAO redisSessionDAO() {
//		RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
//		redisSessionDAO.setRedisManager(redisManager());
//		return redisSessionDAO;
//	}
//
//	/**
//	 * Session Manager 使用的是shiro-redis开源插件
//	 */
//	@Bean
//	public DefaultWebSessionManager sessionManager() {
//		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//		sessionManager.setSessionDAO(redisSessionDAO());
//		return sessionManager;
//	}
//
//	/**
//	 * cookie对象;
//	 * 
//	 * @return
//	 */
//	public SimpleCookie rememberMeCookie() {
//		// 这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
//		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
//		// <!-- 记住我cookie生效时间30天 ,单位秒;-->
//		simpleCookie.setMaxAge(2592000);
//		return simpleCookie;
//	}
//
//	/**
//	 * cookie管理对象;记住我功能
//	 * 
//	 * @return
//	 */
//	public CookieRememberMeManager rememberMeManager() {
//		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//		cookieRememberMeManager.setCookie(rememberMeCookie());
//		// rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
//		cookieRememberMeManager.setCipherKey(Base64.decode("3AvVhmFLUs0KTA3Kprsdag=="));
//		return cookieRememberMeManager;
//	}
//
//	/**
//	 * 限制同一账号登录同时登录人数控制
//	 * 
//	 * @return
//	 */
//	public KickoutSessionControlFilter kickoutSessionControlFilter() {
//		KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
//		// 使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
//		// 这里我们还是用之前shiro使用的redisManager()实现的cacheManager()缓存管理
//		// 也可以重新另写一个，重新配置缓存时间之类的自定义缓存属性
//		kickoutSessionControlFilter.setCacheManager(cacheManager());
//		// 用于根据会话ID，获取会话进行踢出操作的；
//		kickoutSessionControlFilter.setSessionManager(sessionManager());
//		// 是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；踢出顺序。
//		kickoutSessionControlFilter.setKickoutAfter(false);
//		// 同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
//		kickoutSessionControlFilter.setMaxSession(1);
//		// 被踢出后重定向到的地址；
//		kickoutSessionControlFilter.setKickoutUrl("/kickout");
//		return kickoutSessionControlFilter;
//	}
//
//	@Bean
//	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
//			@Qualifier("securityManager") SecurityManager securityManager) {
//		log.info("authorizationAttributeSourceAdvisor()");
//		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
//		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
//		return authorizationAttributeSourceAdvisor;
//	}
//}