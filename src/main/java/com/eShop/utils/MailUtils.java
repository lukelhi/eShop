package com.eShop.utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发送邮件的工具类
 */
public class MailUtils {

	public static void sendMail(String email, String emailMsg)
			throws AddressException, MessagingException {

		Properties props = new Properties();
		props.setProperty("mail.transport.protocol", "SMTP");//设置邮件传输协议是smtp
		props.setProperty("mail.host", "smtp.163.com");//设置邮箱主机
		props.setProperty("mail.smtp.auth", "true");//开启 主机验证
		//定义验证信息
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("lhy146016@163.com", "SAASIOHVRZIQXIAY");
			}
		};

		Session session = Session.getInstance(props, auth);
		//设置发送的信息
		Message message = new MimeMessage(session);
		//设置发送方
		message.setFrom(new InternetAddress("lhy146016@163.com"));

		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(email));
		//设置主题
		message.setSubject("用户激活");
		// message.setText("这是一封激活邮件，请<a href='#'>点击</a>");
		//设置邮件的激活方式
		message.setContent(emailMsg, "text/html;charset=utf-8");
		Transport.send(message);
	}
}
