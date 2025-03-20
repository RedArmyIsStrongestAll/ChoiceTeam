package ru.mephi.atomhack.Skaifom.choiceTeam.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.mephi.atomhack.Skaifom.choiceTeam.configurations.webfilters.RoleFromHeaderFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain config(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) ->
                        authorize
                                .requestMatchers("/swag").permitAll()
                                .requestMatchers("/api/**").permitAll()
                                .requestMatchers("/swagger-ui/**", "/swagger-ui/**.css",
                                        "/swagger-ui/**.js", "/swagger-ui/**.html").permitAll()

                                .anyRequest().authenticated())
//                .addFilterAfter(new RoleFromHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) ->
                        session.maximumSessions(1))
        ;
        return http.build();
    }
}
