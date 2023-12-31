package aladdin.kaineus.what2eat.global.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

        http
                .csrf(AbstractHttpConfigurer::disable);

        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(new MvcRequestMatcher(introspector,"/**")).permitAll()
                                .anyRequest().authenticated()
                );

        return http.build();
    }

}
