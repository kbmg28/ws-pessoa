package br.com.kbmg.utils;

import java.security.InvalidParameterException;

public class Validator {

	public static boolean verifyIsNullOrEmpty(String str) {
		return (str == null || str.trim().isEmpty());
	}

	public static Long stringParseLong(String param, String nomeDoParametro) {
		try {
			return Long.parseLong(param);
		} catch (Exception e) {
			 throw new InvalidParameterException(nomeDoParametro + " inválido.");
		}
	}
}
