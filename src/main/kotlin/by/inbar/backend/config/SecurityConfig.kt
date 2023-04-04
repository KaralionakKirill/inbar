package by.inbar.backend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthFilter: JwtAuthFilter,

    private val authenticationProvider: AuthenticationProvider,

    private val logoutHandler: LogoutHandler
) {
    private val AUTH_WHITELIST = arrayOf(
        "/api/auth/**"
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .csrf().disable()
            .cors().configurationSource(corsConfigurationSource())
            .and()
            .authorizeHttpRequests()
            .requestMatchers(*AUTH_WHITELIST).permitAll()
            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter::class.java)
            .logout()
            .logoutUrl("/api/auth/logout").addLogoutHandler(logoutHandler)
            .logoutSuccessHandler { _, _, _ -> SecurityContextHolder.clearContext() }
            .and()
            .build()
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", corsConfiguration())
        }
    }

    @Bean
    fun corsConfiguration(): CorsConfiguration {
        return CorsConfiguration().apply {
            allowCredentials = true
            addAllowedHeader("*")
            addAllowedOriginPattern("*")
            addAllowedMethod("*")
        }
    }
}
