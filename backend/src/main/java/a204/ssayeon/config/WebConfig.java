package a204.ssayeon.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//cors 설정
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private static final String DEVELOP_FRONT_ADDRESS = "http://localhost:3000";
    private static final String DEPLOY_FRONT_ADDRESS = "http://ssayeon.co.kr";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(DEVELOP_FRONT_ADDRESS, DEPLOY_FRONT_ADDRESS)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .exposedHeaders("location")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
