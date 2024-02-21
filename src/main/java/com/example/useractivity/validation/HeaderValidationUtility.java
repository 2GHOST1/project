package com.example.useractivity.validation;

import com.example.useractivity.entity.User;
import com.example.useractivity.exception.RequestLimitException;
import com.example.useractivity.repository.UserTrackRepository;

import lombok.extern.slf4j.Slf4j;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.directory.InvalidAttributesException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class HeaderValidationUtility {
	@Autowired
	private UserTrackRepository userTrackRepository;

	public boolean validateAndTrackRequest(String userToken)
			throws InvalidAttributesException, BadRequestException, ParseException {
		User user = userTrackRepository.findByToken(userToken);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

		Date firstDate = new Date();
		Date secondDate = sdf.parse(user.getRequestDate());

		long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
		long intervalTime = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
		
		if (Objects.nonNull(user) && user.getTotalRequest() <= 200 && intervalTime <= 1) {
			user.setTotalRequest(user.getTotalRequest() + 1);
			userTrackRepository.save(user);
			return true;
		}
		log.info("request limit being exceeded and prompting the user to subscribe to a premium package ");
		throw new RequestLimitException("request limit being exceeded and prompting the user to subscribe to a premium package ");
	}



}
