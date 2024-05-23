package com.medilabo.microservicepatients.utils;

public class RegexConstant {
	public static final String REGEX_PHONE = "\\d{3}[-]\\d{3}[-]\\d{4}$";
	public static final String REGEX_ADDRESS = "^\\d(.+)\s(\\S+)$";
	public static final String REGEX_DATE = "^\\d{4}[-]\\d{2}[-]\\d{2}$";
	public static final String REGEX_GENDER="^[M]|[F]";

	private RegexConstant() {

	}
}
