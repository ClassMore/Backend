package com.dev.classmoa.controller;

import java.io.UnsupportedEncodingException;

import com.dev.classmoa.service.MailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dev.classmoa.dto.mail.request.CodeVerificationRequest;
import com.dev.classmoa.dto.mail.request.EmailCheckRequest;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MailController {

	private final MailService mailService;

	@PostMapping("/email/check")
	public ResponseEntity<Void> EmailCheck(@RequestBody EmailCheckRequest request) throws MessagingException,
		UnsupportedEncodingException {
		String authCode = mailService.sendEmail(request);
		//        return authCode;
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/email/verification")
	public ResponseEntity<Boolean> verificationEmail(@RequestBody CodeVerificationRequest request) {
		Boolean response = mailService.verifiedCode(request);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
