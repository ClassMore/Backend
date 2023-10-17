package com.dev.classmoa.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.EmailVerification;
import com.dev.classmoa.domain.repository.EmailVerificationRepository;
import com.dev.classmoa.dto.mail.request.CodeVerificationRequest;
import com.dev.classmoa.dto.mail.request.EmailCheckRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
	private final JavaMailSender emailSender;
	private final EmailVerificationRepository emailRepository;
	private String authNum; // 인증 번호

	// 인증번호 8자리 무작위 생성
	public void createCode() {
		Random random = new Random();
		StringBuffer key = new StringBuffer();

		for (int i = 0; i < 8; i++) {
			int idx = random.nextInt(3);

			switch (idx) {
				case 0 -> key.append((char)((int)random.nextInt(26) + 97));
				case 1 -> key.append((char)((int)random.nextInt(26) + 65));
				case 2 -> key.append(random.nextInt(9));
			}
		}
		authNum = key.toString();
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
		msgOfEmail += authNum + "</strong><div><br/> ";
		msgOfEmail += "</div>";

		message.setFrom(setFrom);
		message.setText(msgOfEmail, "utf-8", "html");

		return message;
	}

	//실제 메일 전송
	public String sendEmail(EmailCheckRequest request) throws MessagingException {
		//메일전송에 필요한 정보 설정
		MimeMessage emailForm = createEmailForm(request.getEmail());

		//실제 메일 전송
		emailSender.send(emailForm);

		LocalDateTime now = LocalDateTime.now();
		//저장
		EmailVerification emailVerification = EmailVerification.builder()
			.email(request.getEmail())
			.verificationCode(authNum)
			.sendTime(now)
			.expiredTime(now.minusMinutes(3L))
			.build();
		emailRepository.save(emailVerification);

		return authNum; //인증 코드 반환
	}

	private void checkDuplicatedEmail(EmailCheckRequest request) {
		Optional<EmailVerification> emailVerification = emailRepository.findByEmail(request.getEmail());
		if (emailVerification.isPresent()) {
			log.debug("MailService.checkDuplicatedEmail exception occur email: {}", request.getEmail());
		}
	}

	public boolean verifiedCode(CodeVerificationRequest request) {
		// 객체로부터 이메일 주소와 사용자가 입력한 인증 코드 추출
		String email = request.getEmail();
		String authCode = request.getAuthCode();

		// 중복 이메일 체크
		EmailCheckRequest emailCheckRequest = new EmailCheckRequest();
		emailCheckRequest.setEmail(email);
		checkDuplicatedEmail(emailCheckRequest);

		// 저장된 인증 코드 가져오기
		Optional<EmailVerification> emailVerificationOptional = emailRepository.findByEmail(email);
		String savedAuthCode = emailVerificationOptional.map(EmailVerification::getVerificationCode).orElse(null);

		// 저장된 인증 코드와 사용자가 입력한 인증 코드 비교
		return savedAuthCode != null && savedAuthCode.equals(authCode);
	}
}