package top.ijiujiu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/scripts/**").permitAll()
                .antMatchers("/styles/**").permitAll()
                .antMatchers("/rest/repairer/**").permitAll()
                .antMatchers("/houseapply/queryone").permitAll()
                .antMatchers("/repaireapply/queryone").permitAll()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll();
    }
}
