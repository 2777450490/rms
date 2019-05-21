package top.ijiujiu.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import top.ijiujiu.component.FuryAuthFailureHandler;
import top.ijiujiu.component.FuryAuthSuccessHandler;
import top.ijiujiu.component.MyLogoutSuccessHandler;
import top.ijiujiu.component.RestAuthAccessDeniedHandler;
import top.ijiujiu.service.IUserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUserService userService;
    /**
     * 依赖注入自定义的登录成功处理器
     */
    @Autowired
    private FuryAuthSuccessHandler furyAuthSuccessHandler;
    /**
     * 依赖注入自定义的登录失败处理器
     */
    @Autowired
    private FuryAuthFailureHandler furyAuthFailureHandler;
    /**
     * 依赖注入自定义的注销成功的处理器
     */
    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;
    /**
     * 依赖注入没有权限的处理器
     */
    @Autowired
    private RestAuthAccessDeniedHandler restAuthAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .anyRequest() // 任何请求
                .authenticated() // 都需要身份认证
                .and().formLogin()
                .loginProcessingUrl("/login")
//                .loginPage("/login").permitAll()
                .successHandler(furyAuthSuccessHandler)
//                .failureForwardUrl("/index") //登陆后跳转页面
                .failureHandler(furyAuthFailureHandler)
//                .failureUrl("/login?error").permitAll()
                .and().logout().logoutUrl("/logout")
                .logoutSuccessHandler(myLogoutSuccessHandler)
                .and().exceptionHandling().accessDeniedHandler(restAuthAccessDeniedHandler)
                .and().cors()//新加入
                .and().csrf().disable();// 取消跨站请求伪造防护
//                .headers().frameOptions().disable();//禁用防止网页被Frame
//                .antMatchers("/swagger-ui.html").permitAll()
//                .antMatchers("/favicon.ico").permitAll()
//                .antMatchers("/swagger-resources/**").permitAll()
//                .antMatchers("/v2/**").permitAll()
//                .antMatchers("/webjars/**").permitAll()
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


}
