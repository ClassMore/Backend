package com.dev.classmoa.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.dev.classmoa.auth.resolver.CustomMethodResolver;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new CustomMethodResolver());
	}
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//			.allowedOriginPatterns("http://localhost:3000")
//			.allowedMethods("*")
//			.allowedHeaders("*")
//			.allowCredentials(false)
//			.exposedHeaders("Authorization");
//	}
}
