package inhatc.group.mojuk.config.ymlConfig;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//여러단계일 경우 이런식으로 객체를 만들어 주어야 함
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Datasource {
	private String driver;
	private String url;
	private String username;
	private String password;
}
