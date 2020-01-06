package br.com.kbmg.enums.converter;

import java.util.stream.Stream;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.com.kbmg.enums.TipoDeUsoEnum;

@Converter(autoApply = true)
public class TipoDeUsoConverter implements AttributeConverter<TipoDeUsoEnum, String> {

	@Override
	public String convertToDatabaseColumn(TipoDeUsoEnum tipoObjeto) {
		return (tipoObjeto == null) ? null : tipoObjeto.getTipo();
	}

	@Override
	public TipoDeUsoEnum convertToEntityAttribute(String tipoBanco) {
		return (tipoBanco == null) ? null
				: Stream.of(TipoDeUsoEnum.values()).filter(s -> s.getTipo().equals(tipoBanco)).findFirst()
						.orElseThrow(IllegalArgumentException::new);
	}
}
