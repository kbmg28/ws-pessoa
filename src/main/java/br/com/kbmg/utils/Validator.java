package br.com.kbmg.utils;

public class Validator {

	public static boolean verifyIsNullOrEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}
}
