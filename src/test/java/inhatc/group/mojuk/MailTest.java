package inhatc.group.mojuk;

import inhatc.group.mojuk.config.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MailTest {
	
	@Autowired
	MailService service;
	
	@Test
	public void sendMail() {
		service.mailSend();
	}
}
