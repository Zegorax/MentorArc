package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomLoginSuccessHandler successHandler;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/allMentor").permitAll()
                .antMatchers("/formMentor").permitAll()
                .antMatchers("/insertMentor").permitAll()
                .antMatchers("/allPoulain").permitAll()
                .antMatchers("/formPoulain").permitAll()
                .antMatchers("/insertPoulain").permitAll()
                .antMatchers("/allHelpRequest").permitAll()
                .antMatchers("/formHelpRequest").hasAnyAuthority("POULAIN")
                .antMatchers("/login").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/admin/**").hasAnyAuthority("ADMIN")
                .antMatchers("/member/**").hasAnyAuthority("POULAIN", "MENTOR")
                .and()
            .csrf().disable().formLogin()
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .successHandler(successHandler)
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
            .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(
            "select email, password, '1' as enabled from auth_user where email=? and status='VERIFIED'"
        ).authoritiesByUsernameQuery(
            "select u.email, r.role_name from auth_user u inner join auth_user_role ur on(u.auth_user_id=ur.auth_user_id) inner join auth_role r on(ur.auth_role_id=r.auth_role_id) where u.email=?"
        ).dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }

}