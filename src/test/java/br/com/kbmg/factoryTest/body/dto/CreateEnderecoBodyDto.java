package br.com.kbmg.factoryTest.body.dto;

import br.com.kbmg.dto.body.EnderecoBodyDto;
import br.com.kbmg.enums.TipoEnderecoEnum;

public class CreateEnderecoBodyDto {

	public static EnderecoBodyDto get(String logradouro, String bairro, String numero) {
		return new EnderecoBodyDto() {
			{
				setLogradouro(logradouro);
				setBairro(bairro);
				setNumero(numero);
				setTipoEndereco(TipoEnderecoEnum.FISCAL);
			}
		};
	}
}
