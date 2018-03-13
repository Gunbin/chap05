package total.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	JavaMailSender mailSender;

	public boolean sendWelcomMail(String target) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			// 받는사람
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			// 보내는 사람 - google서버같은경우는 이 설정을 무시함
			message.setFrom(new InternetAddress("administrator@spring.io"));
			// 제목
			message.setSubject("[SpringIO] 가입을 축하드립니다.");
			// 내용
			String content = "가입을 축하드립니다.\n밤바바밤밤";
			message.setContent(content, "text/plain;charset=utf-8");
			// content설정을 text/html로 하게되면 HTML로 전송할 수도 있다.
			// 보내기
			mailSender.send(message);
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
			return false;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}

	}
}
