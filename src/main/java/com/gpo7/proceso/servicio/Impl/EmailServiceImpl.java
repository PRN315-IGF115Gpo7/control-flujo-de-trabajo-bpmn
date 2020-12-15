package com.gpo7.proceso.servicio.Impl;

import java.nio.charset.StandardCharsets;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.gpo7.proceso.entity.Mail;
import com.gpo7.proceso.servicio.EmailService;

@Service("emailServiceImpl")
public class EmailServiceImpl implements EmailService{
	
	@Autowired
    private JavaMailSender emailSender;
	@Autowired
    private SpringTemplateEngine templateEngine;

	@Override
	public void sendEmail(Mail mail) {
		try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email/email-desbloqueo-plantilla", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            System.out.println("Mail: ------------------------------ " + mail.getFrom());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e){
            throw new RuntimeException(e);
        }
	}
}
