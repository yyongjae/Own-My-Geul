package omg.omgspringbootapp.global.config

import omg.omgspringbootapp.global.interceptor.JwtInterceptor
import omg.omgspringbootapp.global.utils.jwt.JwtUtil
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val jwtUtil: JwtUtil
) : WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080", "http://localhost:3000") // 허용할 출처
            .allowedMethods("GET", "POST") // 허용할 HTTP method
            .allowCredentials(true) // 쿠키 인증 요청 허용
            .maxAge(3000) // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(JwtInterceptor(jwtUtil))
            .addPathPatterns("/api/v1/**")
            .excludePathPatterns("/api/v1/member/login", "/api/v1/member/join")
    }
}