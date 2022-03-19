package inhatc.group.mojuk.config.ymlConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//value를 통해 yml파일 위치를 명시
@PropertySource(value = "classpath:application.yml", factory = YmlPropertySourceFactory.class)
// yml 파일에서 가져올 첫번째 변수명 명시(abc.def.ghi 면 abc 입력)
@ConfigurationProperties(prefix = "spring")
@Setter
@Getter
public class ApplicationYmlRead {
	//private 자료형 변수명(변수명과 속성값은 일치해야 함), 위에서 abc를 입력했으니 def에 해당하는 변수를 입력할 수 있음)
	private Datasource datasource;
}
