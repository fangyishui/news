package com.mynews.controller;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class MailController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private JavaMailSenderImpl javaMailSenderImpl;
	
	 /**
     * 配置文件中我的qq邮箱
     */
	 @Value("${mail.fromMail.addr}")
	 private String from;
	
//	@GetMapping("sendMail")
//	public String sendMail() {
//		
//		
//	}
	
}
