package com.dev.classmoa.auth.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.dev.classmoa.auth.annotation.LoginMember;
import com.dev.classmoa.dto.Member.LoggedInMember;

public class CustomMethodResolver implements HandlerMethodArgumentResolver {
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		boolean isAnnotation = parameter.hasParameterAnnotation(LoginMember.class);
		boolean isLogginedMember = LoggedInMember.class.equals(parameter.getParameterType());
		return isLogginedMember && isAnnotation;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return (LoggedInMember) SecurityContextHolder.getContext()
			.getAuthentication().getPrincipal();
	}
}
