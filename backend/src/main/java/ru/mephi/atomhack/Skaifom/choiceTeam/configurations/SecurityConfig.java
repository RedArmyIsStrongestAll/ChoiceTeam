package ru.mephi.atomhack.Skaifom.choiceTeam.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

                                .requestMatchers(HttpMethod.POST, "/expeditions", "/expedition/task", "/expedition/task/subtask").hasRole("MAESTER")
                                .requestMatchers(HttpMethod.GET, "/expeditions", "/expedition", "/expedition/tasks", "/expedition/heroes").hasRole("MAESTER")
                                .requestMatchers(HttpMethod.POST, "/createTeam").hasRole("MAESTER")

                                .requestMatchers(HttpMethod.GET, "/backlog").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.POST, "/expedition/task/subtask").hasRole("LIBRARIAN")
                                .requestMatchers(HttpMethod.DELETE, "/expedition/task/subtask").hasRole("LIBRARIAN")

                                .anyRequest().permitAll())
                .addFilterAfter(new RoleFromHeaderFilter(), UsernamePasswordAuthenticationFilter.class)
                .sessionManagement((session) ->
                        session.maximumSessions(1))
        ;
        return http.build();
    }
}
