
package com.ola.portal;

import com.ola.portal.servicio.AdminServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SeguridadWeb extends WebSecurityConfigurerAdapter {
    
    @Autowired
    public AdminServicio adminServicio;
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminServicio)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    
        @Override
        protected void configure(HttpSecurity http) throws Exception{
          http
            .authorizeHttpRequests()
                    .antMatchers("/css/*","/js/*","/img/*","/**")
                    .permitAll()
            .and().formLogin()
                    .loginPage("/")
                    .loginProcessingUrl("/logincheck")
                    .usernameParameter("usuario")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/index")
                    .permitAll()
            .and().logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/")
                    .permitAll()
            .and().csrf()
                    .disable();
                    
            
        }
}
