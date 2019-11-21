package br.com.kbmg.response;

import java.util.ArrayList;
import java.util.List;

public class ObjectResponse {

	private Object data;
	private List<String> errors = new ArrayList<>();

	public ObjectResponse() {
	}

	public ObjectResponse(Object data) {
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
