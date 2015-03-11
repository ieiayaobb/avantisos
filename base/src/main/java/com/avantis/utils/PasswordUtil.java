package com.avantis.utils;

import org.apache.commons.lang.RandomStringUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PasswordUtil {

	private static final String numbers="1234567890";
	
	private static final String upLetters="QWERTYUIOPASDFGHJKLZXCVBNM";
	
	private static final String lowLetters="qwertyuiopasdfghjklzxcvbnm";
	
	private static final String specialChars="`~!@#$%^&*()_+-={}|[]\\:\";'<>?,./";
	
	public static String ranPwd () {
		String ranPassword="";
		String password=RandomStringUtils.random(1, numbers)+
				RandomStringUtils.random(1, upLetters)+
				RandomStringUtils.random(1, lowLetters)+
				RandomStringUtils.random(1, specialChars)+
				RandomStringUtils.random(2, numbers+upLetters+lowLetters);
		List<String> list = Arrays.asList(password.split(""));
		Collections.shuffle(list);
		for(String s:list) {
			ranPassword+=s;
		}
		return ranPassword;
	}

}
