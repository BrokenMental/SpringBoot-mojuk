package inhatc.group.mojuk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
    private static final String FROM_ADDRESS = "test@naver.com";

    public void mailSend() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("commontoday@naver.com");
        message.setFrom(MailService.FROM_ADDRESS);
        message.setSubject("test");
        message.setText("testing...");

        mailSender.send(message);
    }
}
