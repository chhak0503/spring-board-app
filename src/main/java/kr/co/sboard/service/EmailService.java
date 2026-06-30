package kr.co.sboard.service;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Log4j2
@RequiredArgsConstructor
@Service
public class EmailService {

    @Value("${spring.mail.username}")
    private String sender;

    private final JavaMailSender mailSender;

    public String sendCode(String receiver){

        int code = ThreadLocalRandom.current().nextInt(100_000, 1_000_000);

        String title = "sbaord 인증코드 입니다.";
        String content = "<h1>인증코드는 " + code + "입니다.</h1>";

        MimeMessage message = mailSender.createMimeMessage();
        try {
            message.setFrom(new InternetAddress(sender, "보내는 사람", "UTF-8"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(title);
            message.setContent(content, "text/html;charset=UTF-8");
            mailSender.send(message);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return String.valueOf(code);
    }

    public void verifyCode(){

    }

}
