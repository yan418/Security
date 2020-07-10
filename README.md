## 安全框架、Shiro、SpringSecurity的整合
分为3个小项目，SpringBootShiro、SpringSecurity、Spring-Shiro

=======
# SpringBootShiro 工程项目
搭建SpringBoot工程，与Shiro的整合 <br>
（ SpringBoot + Shiro + Mybatis + thymeleaf ）

## 导入包
``` bash
  // SpringBoot的基本包，数据库相关、druid、工具类的包省略
     省略...
  // 前台使用 thymeleaf 模板
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-thymeleaf</artifactId>
  </dependency>
  <!-- shiro -->
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-spring</artifactId>
      <version>1.5.3</version>
  </dependency>
  <!-- thymeleaf 与 shiro整合 -->
  <dependency>
      <groupId>com.github.theborakompanioni</groupId>
      <artifactId>thymeleaf-extras-shiro</artifactId>
      <version>2.0.0</version>
  </dependency>
  <!-- shiro 与 ehcache整合 -->
  <dependency>
      <groupId>org.apache.shiro</groupId>
      <artifactId>shiro-ehcache</artifactId>
      <version>1.2.2</version>
  </dependency>
```

# SpringBoot 与 Shiro 整合
首先完成SpringBoot基本操作 <br>
查询用户数据、显示登录页、首页等，数据库在每个项目都有对应的数据。<br>
SpringBootShiro 这个工程，密码加密使用MD5盐值加密。

``` bash
步骤1. 创建 ShiroConfig 类
       类加上 @Configuration 注解，作为 Shiro 配置类； 
       第一步  自定义Realm
       第二步  配置shiro安全管理器
       第三步  shiro工厂bean配置
       如果需要加密、缓存配置，也在这里配置
步骤2. 创建 自定义 UserRealm 类 继承 AuthorizingRealm 
       重写 认证 和 授权 两个方法。
步骤3. 创建实体类、对应接口方法
步骤4. 数据库相关配置完成

启动程序:
   用户  密码      角色权限
   yan	 123      ROLE_emp
   zhu	 123      ROLE_boss  ROLE_emp
   q	   123      ROLE_manager
   w	   123456   ROLE_emp
   
ROLE_manager 权限可访问 /level1路径下的页面
ROLE_emp     权限可访问 /level2路径下的页面
ROLE_boss    权限可访问 /level3路径下的页面
```


# Spring-Shiro工程项目
基于SSM 整合 Shiro 安全框架 <br>
基本配置和上面一样，数据库导入当前项目的数据<br>
Spring-Shiro 这个工程，密码加密使用MD5盐值加密。


## 导入包
``` bash
  // SpringMVC的基本包，数据库相关、工具类的包省略
     省略...
  
  <!-- shiro -->
 <dependency>
   <groupId>commons-logging</groupId>
   <artifactId>commons-logging</artifactId>
   <version>1.1.3</version>
 </dependency>
 <dependency>
   <groupId>commons-collections</groupId>
   <artifactId>commons-collections</artifactId>
   <version>3.2.1</version>
 </dependency>
 <dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-core</artifactId>
   <version>1.2.2</version>
 </dependency>
 <dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-web</artifactId>
   <version>1.2.2</version>
 </dependency>
 <dependency>
   <groupId>net.sf.ehcache</groupId>
   <artifactId>ehcache-core</artifactId>
   <version>2.6.10</version>
 </dependency>
 <dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-ehcache</artifactId>
   <version>1.2.2</version>
 </dependency>
 <dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-quartz</artifactId>
   <version>1.2.2</version>
 </dependency>
 <dependency>
   <groupId>org.apache.shiro</groupId>
   <artifactId>shiro-spring</artifactId>
   <version>1.2.2</version>
 </dependency>
 <!-- aspectj 启用aop注解开发需要 -->
 <dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.7.4</version>
 </dependency>
```
# SSM 与 Shiro 整合
``` bash
步骤1. 配置文件 spring-shiro.xml
      （这里配置Shiro相关文件）
步骤2. Shiro缓存文件 ehcache-shiro.xml
步骤3. web.xml 文件也需要配置过滤器shiro
步骤4. 数据库相关配置完成，基本用户查询操作
步骤5. ShiroRealm.java 文件，重写授权和认证方法
步骤6. PermissionName 这个类，编辑注解，在Controller层标注权限信息
启动程序，跟上面工程一样。
```

# SpringSecurity 工程项目
搭建SpringBoot工程，与SpringSecurity的整合 <br>
（ SpringBoot + SpringSecurity + Mybatis + thymeleaf ）

## 导入包
``` bash
  // SpringBoot的基本包，数据库相关、工具类的包省略
     省略...
  // 前台使用 thymeleaf 模板
  <!-- SpringSecurity -->
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-security</artifactId>
  </dependency>
  <!-- SpringSecurity 与 thymeleaf整合 -->
  <dependency>
      <groupId>org.thymeleaf.extras</groupId>
      <artifactId>thymeleaf-extras-springsecurity5</artifactId>
      <version>3.0.4.RELEASE</version>
  </dependency>
```

# SpringBoot 与 SpringSecurity 整合
基本配置和上面一样，数据库导入当前项目的数据<br>
SpringSecurity 这个工程，密码加密使用BCrypt动态盐值加密。
``` bash
步骤1. 创建配置类 Securityconfig，继承 WebSecurityConfigurerAdapter 类
      开启 @Configuration 、@EnableWebSecurity 注解
      重写几个方法
      WebSecurityConfigurerAdapter：自定义Security策略
      AuthenticationManagerBuilder：自定义认证策略
步骤2. 如果想重写 权限信息
       继承 UserDetailsService 类，重写 loadUserByUsername 方法
步骤3. 数据库相关配置完成，基本用户查询操作
启动程序:
   用户  密码      角色权限
   yan	 123      ROLE_emp
   zhu	 123      ROLE_boss  ROLE_emp
   q	   123      ROLE_manager
   w	   123456   ROLE_emp
   
ROLE_manager 权限可访问 /level1路径下的页面
ROLE_emp     权限可访问 /level2路径下的页面
ROLE_boss    权限可访问 /level3路径下的页面
```
