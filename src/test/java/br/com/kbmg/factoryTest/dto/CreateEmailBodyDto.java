package br.com.kbmg.factoryTest.dto;

import br.com.kbmg.dto.body.EmailBodyDto;
import br.com.kbmg.enums.TipoDeUsoEnum;

public class CreateEmailBodyDto {

	public static EmailBodyDto get(String email, TipoDeUsoEnum tipoDeUso) {
		return new EmailBodyDto() {
			{
				setEmail(email);
				setTipoDeUso(tipoDeUso);
			}
		};
	}
}
