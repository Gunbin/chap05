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
			// �޴»��
			message.setRecipient(RecipientType.TO, new InternetAddress(target));
			// ������ ��� - google������������ �� ������ ������
			message.setFrom(new InternetAddress("administrator@spring.io"));
			// ����
			message.setSubject("[SpringIO] ������ ���ϵ帳�ϴ�.");
			// ����
			String content = "������ ���ϵ帳�ϴ�.\n��ٹٹ��";
			message.setContent(content, "text/plain;charset=utf-8");
			// content������ text/html�� �ϰԵǸ� HTML�� ������ ���� �ִ�.
			// ������
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