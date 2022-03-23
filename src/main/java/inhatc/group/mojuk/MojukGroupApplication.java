package inhatc.group.mojuk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
@MapperScan
public class MojukGroupApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		//hot deploy 후 자동 리스타트 하지 않도록 한다, Run Application 프로퍼티에서 false로 설정.
		//완전한 재시작 없음 메인메소드에서 프로퍼티 삽입
		System.setProperty("spring.devtools.restart.enabled", "false");
		
		SpringApplication app = new SpringApplication(MojukGroupApplication.class);
		app.setBannerMode(Mode.OFF);
		app.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MojukGroupApplication.class);
	}
}
