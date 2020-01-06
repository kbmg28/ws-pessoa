package br.com.kbmg.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.kbmg.enums.StatusEnum;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<StatusEnum, String> {

	@Override
	public String convertToDatabaseColumn(StatusEnum statusObjeto) {
		return (statusObjeto == null) ? null : statusObjeto.getStatus();
	}

	@Override
	public StatusEnum convertToEntityAttribute(String statusBanco) {
		return (statusBanco == null) ? null
				: Stream.of(StatusEnum.values()).filter(s -> s.getStatus().equals(statusBanco)).findFirst()
						.orElseThrow(IllegalArgumentException::new);
	}
}
