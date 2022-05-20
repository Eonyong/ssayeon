package a204.ssayeon.config;

import a204.ssayeon.config.auth.CustomAuthenticationProvider;
import a204.ssayeon.config.auth.PrincipalDetailsService;
import a204.ssayeon.config.jwt.JwtAccessDeniedHandler;
import a204.ssayeon.config.jwt.JwtAuthenticationEntryPoint;
import a204.ssayeon.config.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity //해당 파일로 시큐리티를 활성화
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final PrincipalDetailsService principalDetailsService;

    @Bean
    public JwtFilter tokenAuthenticationFilter() {
        return new JwtFilter();
    }

    //비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder encode() {
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    public CustomAuthenticationProvider customAuthenticationProvider() {
        return new CustomAuthenticationProvider(principalDetailsService, encode());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // 기본 REST API만 쓰겠다
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()

                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) //세션 사용 X
                .and()

                //todo : 권한 설정 필요
                .authorizeRequests()
                .anyRequest().permitAll()

                .and()
                .addFilterBefore(tokenAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }


}