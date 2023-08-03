package com.lucky.security.config;

import com.lucky.security.cache.RedisTokenService;
import com.lucky.security.filter.AuthenticationFilter;
import com.lucky.security.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.expression.WebExpressionVoter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * @author: lenka
 * @date: 2023-04-30 2:45 PM
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private DomainUserDetailService domainUserDetailsService;

    @Resource
    private RedisTokenService redisTokenService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private DataSource dataSource;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/test/**")
                .antMatchers("/i18n/**")
                .antMatchers("/doc.html")
                .antMatchers("/swagger-resources/**")
                .antMatchers("/v2/api-docs")
                .antMatchers("/v3/api-docs")
                .antMatchers("/webjars/**")
        ;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 需要进行认证的路径
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginAuthenticationEntryPoint())
                .accessDeniedHandler(new LoginAccessDeniedHandler())
                .and()
                .rememberMe() // 启用"记住我"功能
                .tokenRepository(persistentTokenRepository())
                .userDetailsService(domainUserDetailsService)
                .tokenValiditySeconds(3600)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new LoginAuthenticationEntryPoint())
                .accessDeniedHandler(new LoginAccessDeniedHandler())
                .and()
                .formLogin()
                // 配置自定义的登录页面
                .loginPage("/login")
                // 登录页面允许所有用户访问
                .permitAll()
                // 如果successForwardUrl和successHandler同时存在，那么只有一个会生效，而且是谁在后面谁生效
                // 配置登录成功后的转发URL
                //.successForwardUrl("/index")
                // 配置自定义的登录成功处理程序
                .successHandler(new LoginSuccessHandler(redisTokenService))
                // 配置自定义的登录失败处理程序
                .failureHandler(new LoginFailureHandler())
                .and()
                .logout()
                // 配置自定义的登出URL
                .logoutUrl("/logout")
                // 配置自定义的登出成功处理程序
                .logoutSuccessHandler(new RestLogoutSuccessHandler(redisTokenService))
                .and()
                .sessionManagement()
                // 不使用会话
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf()
                .disable(); // 禁用跨站请求伪造保护

        // 在UsernamePasswordAuthenticationFilter之前添加自定义的过滤器
        http.addFilterBefore(new AuthenticationFilter(redisTokenService), UsernamePasswordAuthenticationFilter.class);
    }
    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setHideUserNotFoundExceptions(false);
        provider.setUserDetailsService(domainUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        // 配置数据源
        jdbcTokenRepository.setDataSource(dataSource);
        jdbcTokenRepository.setCreateTableOnStartup(false);
        return jdbcTokenRepository;
    }
}
