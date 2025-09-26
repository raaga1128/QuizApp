package isil.java_quiz_server.config;  // ✅ Use your actual package name

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Apply CORS to all endpoints
                .allowedOrigins("http://localhost:3001") // ✅ Allow requests from React app
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // ✅ Allow common HTTP methods
                .allowedHeaders("*") // ✅ Allow any headers
                .allowCredentials(true); // ✅ Allow sending cookies/auth headers if needed
    }
}
