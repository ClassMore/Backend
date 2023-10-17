package com.dev.classmoa.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.dev.classmoa.domain.entity.SocialLoginPlatform;
import com.dev.classmoa.domain.repository.SocialLoginPlatformRepository;
import com.dev.classmoa.exception.ClassmoaException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SocialLoginPlatformService {
	private final SocialLoginPlatformRepository repository;

	public SocialLoginPlatform findSocialPlatform(String platformName){
		return repository.findByPlatformName(platformName)
			.orElseThrow(() -> new ClassmoaException(platformName + "이 없습니다.", HttpStatus.NOT_FOUND));
	}
}
