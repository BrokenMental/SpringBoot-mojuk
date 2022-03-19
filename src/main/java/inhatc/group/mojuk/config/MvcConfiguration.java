package inhatc.group.mojuk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer{

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**")
				.addResourceLocations("classpath:/templates/", "classpath:/static/")
				.setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES));
	}
	
	/*
	 * Interceptor를 new()나 @Resource, @Autowired 로 주입할 경우 spring Container에서 관리하지 못하기 때문에
	 * 해당 pakage를 찾을 수 없음, 아래와 같이 Bean을 직접 생성해줘야 함
	 * 참조 : https://eastglow.github.io/back-end/2019/08/01/Spring-Interceptor-%EC%82%AC%EC%9A%A9-%EC%8B%9C-%EC%9D%98%EC%A1%B4%EC%84%B1-%EC%A3%BC%EC%9E%85%EC%9D%B4-%EC%95%88%EB%90%98%EB%8A%94-%EA%B2%BD%EC%9A%B0.html
	 */
	@Bean
	public LoginInterceptor loginInterceptor() {
		return new LoginInterceptor();
	}
	
	/*
	 * 인터셉트 경로 설정
	 * addPathPatterns : 인터셉트할 경로
	 * excludePathPatterns : 인터셉트에서 제외할 경로(addPathPatterns().excludePathPatterns() 와 같은 형식으로 사용)
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor())
				.addPathPatterns("/**")
				.excludePathPatterns("/assets/**", "/", "/index");
	}
}
