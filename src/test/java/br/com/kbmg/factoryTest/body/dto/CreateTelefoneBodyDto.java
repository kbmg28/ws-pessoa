package br.com.kbmg.factoryTest.body.dto;

import br.com.kbmg.dto.body.TelefoneBodyDto;
import br.com.kbmg.enums.TipoDeUsoEnum;

public class CreateTelefoneBodyDto {

	public static TelefoneBodyDto get(Integer numero) {
		return new TelefoneBodyDto() {
			{
				setDdd(92);
				setNumero(numero);
				setContato("ALGUÉM");
				setTipoDeUso(TipoDeUsoEnum.PARTICULAR);
			}
		};
	}
}
