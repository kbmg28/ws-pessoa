package br.com.kbmg.factoryTest.dto;

import java.time.LocalDate;

import br.com.kbmg.dto.EnderecoDto;
import br.com.kbmg.enums.StatusEnum;
import br.com.kbmg.enums.TipoEnderecoEnum;

public class CreateEnderecoDto {

	public static EnderecoDto get(String idEndereco, String logradouro, String bairro, String numero, StatusEnum status,
			String pessoaId) {
		return new EnderecoDto() {
			{
				setIdEndereco(idEndereco);
				setLogradouro(logradouro);
				setBairro(bairro);
				setNumero(numero);
				setTipoEndereco(TipoEnderecoEnum.FISCAL);
				setStatus(status);
				setPessoaId(pessoaId);
			}
		};
	}
}
