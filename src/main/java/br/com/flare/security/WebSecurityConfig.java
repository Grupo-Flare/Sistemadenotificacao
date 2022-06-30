package br.com.flare.security;

import com.azure.spring.cloud.autoconfigure.aad.AadWebSecurityConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends AadWebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        super.configure(httpSecurity);

        httpSecurity
                .authorizeRequests((authorize) -> {
                    authorize
                            .antMatchers("/canais").permitAll()
                            .antMatchers("/", "/Login", "/resources/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                            .antMatchers(HttpMethod.GET, "/notification").hasAuthority("APPROLE_Admin")
                            .antMatchers(HttpMethod.POST, "/notification").hasAnyAuthority("APPROLE_Admin", "APPROLE_UsuarioEnvia")
                            .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("APPROLE_Admin")
                            .antMatchers(HttpMethod.POST, "/user/**").hasAuthority("APPROLE_Admin")
                            .anyRequest().authenticated();
                })
                .oauth2Client(Customizer.withDefaults())
                .csrf().disable();

    }
}
