package com.mynews;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsApplicationTests {

	@Autowired
	JavaMailSenderImpl javaMail;
	
	@Test
	public void contextLoads() {
		
		SimpleMailMessage message = new SimpleMailMessage();
		message.setSubject("通知-今晚开会");
		message.setText("今晚开会");
		message.setTo("13433162789@163.com");
		message.setFrom("355746803@qq.com");
		javaMail.send(message);
	}

}
