package br.com.kbmg.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
	private static ModelMapper me = new ModelMapper();
	
	public static void printObjectConsole(Object obj) {
		try {
			ObjectMapper m = new ObjectMapper();
			System.out.println("--------------- Dados atuais de " + obj.getClass() + " ---------------");
			System.err.println(m.writeValueAsString(obj));
		} catch (Exception e) {
			System.err.println("Não foi possível mostrar objeto. Erro: " + e.getMessage());
		}
	}

	public static Object convertObject(Object obj, Class<?> type) {
		return me.map(obj, type);
	}

	public static void map(Object base, Object destination) {
		me.map(base, destination);
	}

	public static Object convertObjectAndPrintConsole(Object obj, Class<?> type) {
		Object map = me.map(obj, type);
		printObjectConsole(map);
		return map;
	}

	public static List<?> convertList(List<?> list, Class<?> typeConvert) {
		return list.stream().map(e -> Util.convertObject(e, typeConvert)).collect(Collectors.toList());
	}

}
