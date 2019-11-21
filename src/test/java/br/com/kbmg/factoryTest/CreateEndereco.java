package br.com.kbmg.factoryTest;

import br.com.kbmg.domain.Endereco;
import br.com.kbmg.enums.TipoEndereco;

public class CreateEndereco {

	public static Endereco get(Long idEndereco, String logradouro, String codigoIbgeUF, Integer codigoIbgeMunicipio,
			String cep, String bairro, String numero, String complemento, TipoEndereco tipoEndereco) {
		return new Endereco() {
			private static final long serialVersionUID = 1L;
			{
				setIdEndereco(idEndereco);
				setLogradouro(logradouro);
				setCodigoIbgeUF(codigoIbgeUF);
				setCodigoIbgeMunicipio(codigoIbgeMunicipio);
				setCep(cep);
				setBairro(bairro);
				setNumero(numero);
				setComplemento(complemento);
				setTipoEndereco(tipoEndereco);
			}
		};
	}
}
