package com.dev.classmoa.service;

import com.dev.classmoa.dto.mail.request.CodeVerificationRequest;
import com.dev.classmoa.dto.mail.request.EmailCheckRequest;
import com.dev.classmoa.utils.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;
    private final RedisTemplate<String, String> redisTemplate;
    private String authCode; // 인증 번호

    // 인증번호 8자리 무작위 생성
    public void createCode() {
        Random random = new Random();
        StringBuffer key = new StringBuffer();

        for (int i = 0; i < 8; i++) {
            int idx = random.nextInt(3);

            switch (idx) {
                case 0:
                    key.append((char) ((int) random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) ((int) random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(9));
                    break;
            }
        }
        authCode = key.toString();
    }

    // 메일 양식 작성
    public MimeMessage createEmailForm(String email) throws MessagingException {
        createCode();
        String setFrom = "classmoa.corp@gmail.com";
        String title = "클래스모아 인증코드 입니다.";

        MimeMessage message = emailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject(title);

        // 메일 내용
        String msgOfEmail = "";
        msgOfEmail += "<div style='margin:20px;'>";
        msgOfEmail += "<h1> 안녕하세요 클래스모아 입니다. </h1>";
        msgOfEmail += "<br>";
        msgOfEmail += "<p>아래 코드를 입력해주세요<p>";
        msgOfEmail += "<br>";
        msgOfEmail += "<p>감사합니다.<p>";
        msgOfEmail += "<br>";
        msgOfEmail += "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgOfEmail += "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgOfEmail += "<div style='font-size:130%'>";
        msgOfEmail += "CODE : <strong>";
        msgOfEmail += authCode + "</strong><div><br/> ";
        msgOfEmail += "</div>";

        message.setFrom(setFrom);
        message.setText(msgOfEmail, "utf-8", "html");

        redisUtil.setDataExpire(email, authCode, 60 * 3L);

        return message;
    }

    //실제 메일 전송
    public String sendEmail(EmailCheckRequest request) throws MessagingException {
        if (redisUtil.existData(request.getEmail())) {
            redisUtil.deleteData(request.getEmail());
        }

        //메일전송에 필요한 정보 설정
        MimeMessage emailForm = createEmailForm(request.getEmail());

        //실제 메일 전송
        emailSender.send(emailForm);

        return authCode; //인증 코드 반환
    }

    public boolean verifiedCode(CodeVerificationRequest request) {
        if (redisTemplate.hasKey(request.getEmail())) {
            String keyTempNumber = redisTemplate.opsForValue().get(request.getEmail());
            return keyTempNumber.equals(request.getAuthCode());
        }
        return false; // 값이 일치하지 않거나 데이터가 없을 경우 false 반환
    }
}