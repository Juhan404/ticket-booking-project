package com.example.theater.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public DefaultSecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/").permitAll().requestMatchers("/logo.png").permitAll().requestMatchers("/register_and_login_bg.png").permitAll()
                        .requestMatchers("/favicon.ico").permitAll().requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll().requestMatchers("/logout").permitAll().requestMatchers("/now-showing").permitAll()
                        .requestMatchers("/coming-soon").permitAll().requestMatchers("/details/**").permitAll().requestMatchers("/search").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").failureUrl("/login?error=true")
                        // .defaultSuccessUrl("/", true)
                        .successHandler(savedRequestAwareAuthenticationSuccessHandler()))
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .build();
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler savedRequestAwareAuthenticationSuccessHandler() {
        return new SavedRequestAwareAuthenticationSuccessHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
