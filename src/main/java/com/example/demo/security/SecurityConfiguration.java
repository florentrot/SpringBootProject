package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessEventPublishingLogoutHandler;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled " +
                        "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role " +
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder); //here we decrypt
//                .withDefaultSchema()
//                .withUser("operative")
//                .password("12345")
//                .roles("USER")
//                .and()
//                .withUser("florentin")
//                .password("54321")
//                .roles("USER")
//                .and()
//                .withUser("managerUser")
//                .password("1234567890")
//                .roles("ADMIN");
    }

//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/projects/*", "/employees/*","/apiemployees","/apiprojects","/apiusers").hasRole("ADMIN")
                .antMatchers("/projects","/employees").hasAnyRole("ADMIN", "USER")
                .antMatchers("/home").authenticated()
                .antMatchers("/register").permitAll()

                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login");

        // this is just for h2-console AND for postman
        // http.csrf().disable();
        // http.headers().frameOptions().disable();
    }
}
