package top.ijiujiu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.io.PrintWriter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors().and()
                .sessionManagement()
                .sessionFixation()
                .none().and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/error").permitAll()
                .antMatchers("/swagger-ui.html").authenticated()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").failureUrl("/login?error")
                .successHandler((request, response, authentication) -> {
                    PrintWriter writer = response.getWriter();
                    writer.print(true);
                })
                .failureHandler(((request, response, exception) -> {
                    exception.printStackTrace();
                })).permitAll()
                .and().logout().permitAll();
    }
}
