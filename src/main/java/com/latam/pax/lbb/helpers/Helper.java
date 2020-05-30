package com.latam.pax.lbb.helpers;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.latam.pax.lbb.domains.apis.Poem;

@Component
public class Helper {
	
	public Boolean isToday(String birthday) {		
		LocalDate now = LocalDate.now();
		LocalDate birthdayLD = birthdayToLocalDate(birthday);
		String dateNow = now.getDayOfMonth() + "/" + now.getMonthValue();
		String dateBirthday = birthdayLD.getDayOfMonth() + "/" + now.getMonthValue();
		if (dateNow.equals(dateBirthday)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
	
	public Long daysLeftBirthday(String birthday) {
		LocalDate today = LocalDate.now();
        LocalDate birtLocalDate = birthdayToLocalDate(birthday);
        LocalDate nextBDay = birtLocalDate.withYear(today.getYear());
        if (nextBDay.isBefore(today) || nextBDay.isEqual(today)) {
            nextBDay = nextBDay.plusYears(1);
        }
        //Period p = Period.between(today, nextBDay);
        return ChronoUnit.DAYS.between(today, nextBDay);
	}
	
	public Integer getAgePerson(String birthday) {
		LocalDate now = LocalDate.now();
		Period period = Period.between(birthdayToLocalDate(birthday), now);
		return period.getYears();
	}
	
	public String getPoem() {
		RestTemplate restTemplate = new RestTemplate();
		Poem[] poems = restTemplate.getForObject("https://www.poemist.com/api/v1/randompoems", Poem[].class);
		String poem = poems[new Random().nextInt(poems.length)].getContent();
		return StringUtils.abbreviate(poem, 2500);
	}
	
	private LocalDate birthdayToLocalDate(String birthday) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(ConstantsHelper.DD_MM_YYY_FORMAT_DATE);
		LocalDate birthdayLocalDate = LocalDate.parse(birthday, fmt);
		return birthdayLocalDate;
	}
}
