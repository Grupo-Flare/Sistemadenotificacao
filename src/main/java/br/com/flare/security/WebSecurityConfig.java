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
                            .antMatchers("/", "/Login", "/resources/static/**", "/css/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                            .antMatchers(HttpMethod.GET, "/notification").hasAuthority("APPROLE_Admin")
                            .antMatchers(HttpMethod.POST, "/notification").hasAuthority("APPROLE_Admin")
                            .antMatchers(HttpMethod.POST, "/notification").hasAuthority("APPROLE_UsuarioEnvia")
                            .anyRequest().authenticated();
                })
                .oauth2Client(Customizer.withDefaults());

    }
}
