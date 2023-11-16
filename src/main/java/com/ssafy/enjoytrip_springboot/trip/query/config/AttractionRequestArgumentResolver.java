package com.ssafy.enjoytrip_springboot.trip.query.config;

import com.ssafy.enjoytrip_springboot.trip.common.dto.AttractionRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class AttractionRequestArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(AttractionRequest.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

        String areaCode = request.getParameter("areaCode");
        String sigunguCode = request.getParameter("sigunguCode");
        String contentTypeId = request.getParameter("contentTypeId");
        String pageNo = request.getParameter("pageNo");

        AttractionRequest attractionRequest = AttractionRequest.builder()
                .areaCode(isEmptyValue(areaCode) ? 0 : parseValue(areaCode))
                .sigunguCode(isEmptyValue(sigunguCode) ? 0 : parseValue(sigunguCode))
                .contentTypeId(isEmptyValue(contentTypeId) ? 0 : parseValue(contentTypeId))
                .pageNo(isEmptyValue(pageNo) ? 0 : parseValue(pageNo))
                .build();

        return attractionRequest;
    }

    private boolean isEmptyValue(String value) {
        return value == null || value.isEmpty();
    }

    private int parseValue(String value){
        return Integer.parseInt(value);
    }


}
