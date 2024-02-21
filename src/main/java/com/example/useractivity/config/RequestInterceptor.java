package com.example.useractivity.config;

import java.text.ParseException;

import javax.naming.directory.InvalidAttributesException;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.useractivity.exception.RequestLimitException;
import com.example.useractivity.validation.HeaderValidationUtility;

import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor implements HandlerInterceptor {

	@Autowired
	private HeaderValidationUtility headerValidationUtility;

	@Override
	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse response, Object object)
			throws InvalidAttributesException, BadRequestException, ParseException {
		if (StringUtils.isBlank(httpServletRequest.getParameter("token"))) {
			throw new RequestLimitException("Invalid request : ");
		}
		return headerValidationUtility.validateAndTrackRequest(httpServletRequest.getParameter("token"));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object object,
			ModelAndView model) {
		System.out.println("2 - post handle.");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object,
			Exception exception) {
		if (exception != null) {

			System.out.println("An error occured.");
		}
		System.out.println("3 - after completion.");
	}
}