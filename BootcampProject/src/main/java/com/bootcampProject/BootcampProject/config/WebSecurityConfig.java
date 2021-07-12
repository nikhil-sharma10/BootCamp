package com.bootcampProject.BootcampProject.config;

import com.bootcampProject.BootcampProject.convertor.CustomerTransformer;
import com.bootcampProject.BootcampProject.convertor.SellerTransformer;
import com.bootcampProject.BootcampProject.jwt.JwtRequestFilter;
import com.bootcampProject.BootcampProject.service.AuthenticationService;
import com.bootcampProject.BootcampProject.service.CUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CUserDetailsService userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomerTransformer customerTransformer(){return new CustomerTransformer();}

    @Bean
    public SellerTransformer sellerTransformer(){return new SellerTransformer();}

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationService authenticationServiceBean(){
        return new AuthenticationService();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/register/admin").permitAll()
                .and().authorizeRequests().antMatchers("/register/seller").permitAll()
                .and().authorizeRequests().antMatchers("/register/customer").permitAll()
                .and().authorizeRequests().antMatchers("/admin/get-customer").permitAll()
                .and().authorizeRequests().antMatchers("/register/authenticate").permitAll()
                .and().authorizeRequests().antMatchers("/admin/get-seller").hasAuthority("ROLE_SELLER")
                .and().authorizeRequests().antMatchers("/admin/activate-seller").hasAuthority("ROLE_SELLER")
                .and().authorizeRequests().antMatchers("/admin/activate-customer").hasAuthority("ROLE_SELLER")
                .and().authorizeRequests().antMatchers("/admin/deactivate-customer").hasAuthority("ROLE_SELLER")
                .and().authorizeRequests().antMatchers("/admin/deactivate-seller").hasAuthority("ROLE_SELLER")
                .anyRequest().authenticated()
                .and().exceptionHandling().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
