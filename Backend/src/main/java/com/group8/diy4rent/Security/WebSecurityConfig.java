package com.group8.diy4rent.Security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.group8.diy4rent.Security.Jwt.JwtEntryPoint;
import com.group8.diy4rent.Security.Jwt.JwtProvider;
import com.group8.diy4rent.Security.Jwt.JwtProvider;
import com.group8.diy4rent.Security.Jwt.JwtTokenFilter;
import com.group8.diy4rent.Security.Servicios.PropietarioDetailsServiceImpl;
import com.group8.diy4rent.Security.Servicios.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    PropietarioDetailsServiceImpl propietarioDetailsService;

    // Devuelve el mensaje de no autorizado
    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Bean
    public JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }

    /**
     * Encripta el pasword
     * 
     * @return pasword ecriptado
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    // public JwtProvider jwtProvider() {
    //     // Perform any necessary initialization here
    //     return new JwtProvider();
    // }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public AuthenticationManager usuarioAuthenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(usuarioAuthenticationProvider()));
    }

    @Bean
    public DaoAuthenticationProvider usuarioAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager propietarioAuthenticationManager() throws Exception {
        return new ProviderManager(Arrays.asList(propietarioAuthenticationProvider()));
    }

    @Bean
    public DaoAuthenticationProvider propietarioAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(propietarioDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Desactivamos cookies ya que enviamos un token
        // cada vez que hacemos una petición
        http.headers().frameOptions().disable();
        http.cors().and().csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/auth/**", "/usuarios/**", "/propietarios/**", "/herramientas/**","/h2-console/**", "/usuariosName/**", "/propietariosName/**", "/alquileres/herramienta/**", "/alquileres/**", "/swagger-ui/**").permitAll()
                .anyRequest().authenticated()
                .and()
                //.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                //.and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // http.addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

}
