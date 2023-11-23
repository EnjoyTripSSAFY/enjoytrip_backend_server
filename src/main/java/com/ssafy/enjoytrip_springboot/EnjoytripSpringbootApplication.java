package com.ssafy.enjoytrip_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@SpringBootApplication
public class EnjoytripSpringbootApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(EnjoytripSpringbootApplication.class, args);
	}
//
//	private final JwtInterceptor jwtInterceptor;
//
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/auth/**") // 기본 적용 경로
//				.excludePathPatterns(Arrays.asList("/auth/member/**"));// 적용 제외 경로
//	}

}
